package com.society.maintenance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.society.maintenance.dto.BulkFlatRequest;
import com.society.maintenance.dto.BulkOpeningBalanceRequest;
import com.society.maintenance.dto.BulkResult;
import com.society.maintenance.service.FlatService;
import com.society.maintenance.service.OpeningBalanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bulk")
@RequiredArgsConstructor
public class BulkController {
	
	@Autowired
	private FlatService flatService;
	
	@Autowired
	private OpeningBalanceService openingBalService;
	
	@PostMapping("/addflat")
    public BulkResult bulkCreate(@RequestBody BulkFlatRequest request) {
        return flatService.bulkCreate(request.getFlats());
    }
	
	@PostMapping("/opening-balance")
    public BulkResult bulkOpeningBalance(
            @RequestBody BulkOpeningBalanceRequest request) {
        return openingBalService.addBulkOpeningBalances(request.getItems());
    }

}
