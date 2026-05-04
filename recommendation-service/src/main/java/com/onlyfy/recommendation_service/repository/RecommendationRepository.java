package com.onlyfy.recommendation_service.repository;

import com.onlyfy.recommendation_service.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {
}