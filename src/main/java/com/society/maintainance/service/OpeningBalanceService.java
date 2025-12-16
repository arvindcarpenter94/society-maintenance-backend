package com.society.maintenance.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.maintenance.dto.BulkResult;
import com.society.maintenance.dto.OpeningBalanceItem;
import com.society.maintenance.dto.OpeningBalanceRequest;
import com.society.maintenance.entity.Flat;
import com.society.maintenance.entity.Ledger;
import com.society.maintenance.repository.FlatRepository;
import com.society.maintenance.repository.LedgerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpeningBalanceService {

	@Autowired
    private FlatRepository flatRepo;
	
	@Autowired
    private LedgerRepository ledgerRepo;

    @Transactional
    public Ledger addOpeningBalance(OpeningBalanceRequest request) {

        Flat flat = flatRepo.findById(request.getFlatId())
                .orElseThrow(() -> new RuntimeException("Flat not found"));

        // ❌ Prevent duplicate opening balance
        if (ledgerRepo.existsByFlat_IdAndTxnType(flat.getId(), "OPENING_BALANCE")) {
            throw new RuntimeException("Opening balance already exists for this flat");
        }

        BigDecimal amount = request.getAmount();
        BigDecimal debit = BigDecimal.ZERO;
        BigDecimal credit = BigDecimal.ZERO;

        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            debit = amount; // overdue
        } else {
            credit = amount.abs(); // advance
        }

        BigDecimal previousBalance =
                Optional.ofNullable(ledgerRepo.findLastBalance(flat.getId()))
                        .orElse(BigDecimal.ZERO);

        BigDecimal newBalance =
                previousBalance.add(debit).subtract(credit);

        Ledger ledger = new Ledger();
        ledger.setFlat(flat);
        ledger.setTxnDate(request.getDate());
        ledger.setTxnType("OPENING_BALANCE");
        ledger.setDebit(debit);
        ledger.setCredit(credit);
        ledger.setBalance(newBalance);

        return ledgerRepo.save(ledger);
    }
    
    @Transactional
    public BulkResult addBulkOpeningBalances(List<OpeningBalanceItem> items) {

        int created = 0;
        int skipped = 0;

        for (OpeningBalanceItem item : items) {

            if (item.getFlatNumber() == null ||
                item.getOpeningBalance() == null ||
                item.getOpeningBalance().compareTo(BigDecimal.ZERO) == 0) {
                skipped++;
                continue;
            }

            Flat flat = flatRepo.findByFlatNumber(item.getFlatNumber())
                    .orElseThrow(() ->
                        new RuntimeException("Flat not found: " + item.getFlatNumber())
                    );

            // Skip if opening balance already exists
            if (ledgerRepo.existsByFlat_IdAndTxnType(
                    flat.getId(), "OPENING_BALANCE")) {
                skipped++;
                continue;
            }

            BigDecimal opening = item.getOpeningBalance();

            BigDecimal debit = BigDecimal.ZERO;
            BigDecimal credit = BigDecimal.ZERO;
            BigDecimal balance;

            if (opening.compareTo(BigDecimal.ZERO) > 0) {
                // Due
                debit = opening;
                balance = opening;
            } else {
                // Advance
                credit = opening.abs();
                balance = opening; // negative
            }

            Ledger ledger = new Ledger();
            ledger.setFlat(flat);
            ledger.setTxnDate(LocalDate.now());
            ledger.setTxnType("OPENING_BALANCE");
            ledger.setDebit(debit);
            ledger.setCredit(credit);
            ledger.setBalance(balance);

            ledgerRepo.save(ledger);
            created++;
        }
        return new BulkResult(created, skipped);
    }
}
