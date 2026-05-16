package com.onlyfy.payment_service.repository;

import com.onlyfy.payment_service.model.PaymentServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;   

public interface PaymentServiceRepository extends JpaRepository<PaymentServiceModel, Integer> {
}