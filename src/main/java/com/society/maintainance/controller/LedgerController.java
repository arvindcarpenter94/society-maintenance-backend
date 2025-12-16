package com.society.maintenance.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.society.maintenance.dto.BulkOpeningBalanceRequest;
import com.society.maintenance.dto.BulkResult;
import com.society.maintenance.dto.LedgerResponse;
import com.society.maintenance.dto.OpeningBalanceRequest;
import com.society.maintenance.entity.Ledger;
import com.society.maintenance.service.LedgerService;
import com.society.maintenance.service.OpeningBalanceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ledger")
@RequiredArgsConstructor
public class LedgerController {

	@Autowired
	private OpeningBalanceService openingBalService;
	
	@Autowired
	private LedgerService ledgerService;

    @PostMapping("/opening-balance")
    public Ledger addOpeningBalance(
            @Valid @RequestBody OpeningBalanceRequest request) {

        return openingBalService.addOpeningBalance(request);
    }
    // 🔹 Full ledger for a flat
    @GetMapping("/flat/{flatId}")
    public List<LedgerResponse> getLedger(@PathVariable Long flatId) {
        return ledgerService.getLedger(flatId);
    }

    // 🔹 Ledger with date filter
    @GetMapping("/flat/{flatId}/range")
    public List<LedgerResponse> getLedgerByRange(
            @PathVariable Long flatId,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to) {

        return ledgerService.getLedgerByDateRange(flatId, from, to);
    }
}
