package com.onlyfy.recomenations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onlyfy.recomenations.model.RecomendationModel;

public interface RecomendationRepository extends JpaRepository<RecomendationModel, Long> {
}
