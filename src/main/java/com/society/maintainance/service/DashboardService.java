package com.society.maintenance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.maintenance.dto.DashboardSummary;
import com.society.maintenance.repository.LedgerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

	@Autowired
    private LedgerRepository ledgerRepo;

    public DashboardSummary getSummary() {
        return new DashboardSummary(
                ledgerRepo.totalOutstanding(),
                ledgerRepo.totalAdvance(),
                ledgerRepo.flatsWithDue()
        );
    }
}

