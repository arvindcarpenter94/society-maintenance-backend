package com.society.maintenance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.society.maintenance.dto.DashboardSummary;
import com.society.maintenance.dto.FlatDueDTO;
import com.society.maintenance.repository.LedgerRepository;
import com.society.maintenance.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

	@Autowired
    private DashboardService service;
	
	@Autowired
	private LedgerRepository ledgerRepository;

    @GetMapping("/summary")
    public DashboardSummary getSummary() {
        return service.getSummary();
    }
    
    @GetMapping("/due-flats")
    public List<FlatDueDTO> getFlatsWithDue() {
        return ledgerRepository.findFlatsWithOutstandingDue();
    }
}

