package com.onlyfy.subscription_service.repository;

import com.onlyfy.subscription_service.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
}