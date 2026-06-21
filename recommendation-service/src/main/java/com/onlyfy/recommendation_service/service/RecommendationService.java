package com.onlyfy.recommendation_service.service;

import com.onlyfy.recommendation_service.model.Recommendation;
import com.onlyfy.recommendation_service.repository.RecommendationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    // Inyección por constructor (la opción más limpia y recomendada para testing)
    public RecommendationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    /**
     * Obtiene la lista de recomendaciones asociadas a un usuario específico.
     */
    public List<Recommendation> obtenerPorUsuario(Long userId) {
        return recommendationRepository.findByUserId(userId);
    }

    /**
     * Busca una recomendación específica por su ID único.
     */
    public Optional<Recommendation> obtenerPorId(Long id) {
        return recommendationRepository.findById(id);
    }

    /**
     * Guarda o actualiza una recomendación en el sistema.
     */
    public Recommendation guardar(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }
}