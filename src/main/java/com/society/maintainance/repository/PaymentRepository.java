package com.society.maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.society.maintenance.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}
