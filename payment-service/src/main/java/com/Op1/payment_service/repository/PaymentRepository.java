package com.Op1.payment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Op1.payment_service.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
