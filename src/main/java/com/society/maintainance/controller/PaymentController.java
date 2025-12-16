package com.society.maintenance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.society.maintenance.dto.PaymentRequest;
import com.society.maintenance.entity.Payment;
import com.society.maintenance.service.PaymentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

	@Autowired
    private PaymentService service;

    @PostMapping
    public Payment recordPayment(@Valid @RequestBody PaymentRequest request) {
        return service.recordPayment(request);
    }
}

