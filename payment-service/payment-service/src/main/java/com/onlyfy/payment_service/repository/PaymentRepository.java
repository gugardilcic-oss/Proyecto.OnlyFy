package com.onlyfy.payment_service.repository;

import com.onlyfy.payment_service.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;   

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}