package com.society.maintenance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.society.maintenance.service.MaintenanceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

	@Autowired
    private MaintenanceService service;

    @PostMapping("/generate")
    public String generate(@RequestParam String month) {
        service.generateMonthlyMaintenance(month);
        return "Maintenance generated for " + month;
    }
}
