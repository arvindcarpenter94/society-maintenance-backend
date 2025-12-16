package com.society.maintenance.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.maintenance.dto.PaymentRequest;
import com.society.maintenance.entity.Flat;
import com.society.maintenance.entity.Ledger;
import com.society.maintenance.entity.Payment;
import com.society.maintenance.repository.FlatRepository;
import com.society.maintenance.repository.LedgerRepository;
import com.society.maintenance.repository.PaymentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	@Autowired
    private FlatRepository flatRepo;
	
	@Autowired
    private PaymentRepository paymentRepo;
	
	@Autowired
    private LedgerRepository ledgerRepo;

    @Transactional
    public Payment recordPayment(PaymentRequest request) {

        Flat flat = flatRepo.findById(request.getFlatId())
                .orElseThrow(() -> new RuntimeException("Flat not found"));

        // 1️⃣ Save payment
        Payment payment = new Payment();
        payment.setFlat(flat);
        payment.setAmount(request.getAmount());
        payment.setPaymentDate(request.getPaymentDate());
        payment.setPaymentMode(request.getPaymentMode());
        payment.setReferenceNo(request.getReferenceNo());

        paymentRepo.save(payment);

        // 2️⃣ Get previous balance
        BigDecimal previousBalance =
                Optional.ofNullable(ledgerRepo.findLastBalance(flat.getId()))
                        .orElse(BigDecimal.ZERO);

        // 3️⃣ Calculate new balance (credit)
        BigDecimal newBalance =
                previousBalance.subtract(request.getAmount());

        // 4️⃣ Insert ledger entry
        Ledger ledger = new Ledger();
        ledger.setFlat(flat);
        ledger.setTxnDate(request.getPaymentDate());
        ledger.setTxnType("PAYMENT");
        ledger.setDebit(BigDecimal.ZERO);
        ledger.setCredit(request.getAmount());
        ledger.setBalance(newBalance);

        ledgerRepo.save(ledger);

        return payment;
    }
}

