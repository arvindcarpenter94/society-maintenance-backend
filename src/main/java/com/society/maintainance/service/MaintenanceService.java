package com.society.maintenance.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.maintenance.entity.Flat;
import com.society.maintenance.entity.Ledger;
import com.society.maintenance.entity.MaintenanceBill;
import com.society.maintenance.entity.SocietyRate;
import com.society.maintenance.repository.FlatRepository;
import com.society.maintenance.repository.LedgerRepository;
import com.society.maintenance.repository.MaintenanceBillRepository;
import com.society.maintenance.repository.SocietyRateRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

	@Autowired
    private FlatRepository flatRepo;
	
	@Autowired
    private SocietyRateRepository rateRepo;
	
	@Autowired
    private MaintenanceBillRepository billRepo;
	
	@Autowired
    private LedgerRepository ledgerRepo;

    @Transactional
    public void generateMonthlyMaintenance(String monthYear) {

        SocietyRate rate = rateRepo.findByEffectiveToIsNull()
                .orElseThrow(() -> new RuntimeException("Rate not configured"));

        List<Flat> flats = flatRepo.findAll();

        for (Flat flat : flats) {
            BigDecimal amount =
                    rate.getRatePerSqft().multiply(BigDecimal.valueOf(flat.getAreaSqft()));
            
            if (billRepo.existsByFlat_IdAndMonthYear(flat.getId(), monthYear)) {
                continue;
            }

            MaintenanceBill bill = new MaintenanceBill();
            bill.setFlat(flat);
            bill.setMonthYear(monthYear);
            bill.setRatePerSqft(rate.getRatePerSqft());
            bill.setAreaSqft(flat.getAreaSqft());
            bill.setMaintenanceAmount(amount);
            bill.setGeneratedOn(LocalDate.now());
            bill.setStatus("GENERATED");

            billRepo.save(bill);
            
            BigDecimal previousBalance =
                    Optional.ofNullable(
                            ledgerRepo.findLastBalance(flat.getId())
                    ).orElse(BigDecimal.ZERO);
            
            BigDecimal newBalance =
                    previousBalance.add(amount);

            Ledger ledger = new Ledger();
            ledger.setFlat(flat);
            ledger.setTxnDate(LocalDate.now());
            ledger.setTxnType("MAINTENANCE");
            ledger.setDebit(amount);
            ledger.setCredit(BigDecimal.ZERO);
            ledger.setBalance(newBalance);
            ledgerRepo.save(ledger);
        }
    }
}

