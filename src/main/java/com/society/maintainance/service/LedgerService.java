package com.society.maintenance.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.maintenance.dto.LedgerResponse;
import com.society.maintenance.repository.FlatRepository;
import com.society.maintenance.repository.LedgerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LedgerService {

	@Autowired
    private LedgerRepository ledgerRepo;
	
	@Autowired
    private FlatRepository flatRepo;

    @Transactional
    public List<LedgerResponse> getLedger(Long flatId) {

        // Validate flat exists
        flatRepo.findById(flatId)
                .orElseThrow(() -> new RuntimeException("Flat not found"));

        return ledgerRepo
                .findByFlat_IdOrderByIdAsc(flatId)
                .stream()
                .map(l -> new LedgerResponse(
                        l.getTxnDate(),
                        l.getTxnType(),
                        l.getDebit(),
                        l.getCredit(),
                        l.getBalance()
                ))
                .toList();
    }

    @Transactional
    public List<LedgerResponse> getLedgerByDateRange(
            Long flatId,
            LocalDate from,
            LocalDate to) {

        flatRepo.findById(flatId)
                .orElseThrow(() -> new RuntimeException("Flat not found"));

        return ledgerRepo
                .findLedgerByDateRange(flatId, from, to)
                .stream()
                .map(l -> new LedgerResponse(
                        l.getTxnDate(),
                        l.getTxnType(),
                        l.getDebit(),
                        l.getCredit(),
                        l.getBalance()
                ))
                .toList();
    }
}

