package com.onlyfy.recomenations.service;

import com.onlyfy.recomenations.model.RecomendationModel;
import com.onlyfy.recomenations.repository.RecomendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecomendationService {

    private final RecomendationRepository recomendationRepository;

    @Autowired
    public RecomendationService(RecomendationRepository recomendationRepository) {
        this.recomendationRepository = recomendationRepository;
    }

    public List<RecomendationModel> getAllRecomendations() {
        return recomendationRepository.findAll();
    }

    public RecomendationModel createRecomendation(RecomendationModel recomendation) {
        return recomendationRepository.save(recomendation);
    }
}
