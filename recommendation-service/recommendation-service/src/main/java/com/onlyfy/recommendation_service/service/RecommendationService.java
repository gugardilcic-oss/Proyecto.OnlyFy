package com.onlyfy.recommendation_service.service;

import com.onlyfy.recommendation_service.dto.RecommendationRequestDTO;
import com.onlyfy.recommendation_service.dto.RecommendationResponseDTO;
import com.onlyfy.recommendation_service.model.Recommendation;
import com.onlyfy.recommendation_service.repository.RecommendationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private static final Logger logger = LoggerFactory.getLogger(RecommendationService.class);

    private final RecommendationRepository recommendationRepository;

    public RecommendationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    public List<RecommendationResponseDTO> listar() {
        logger.info("Listando todas las recomendaciones");
        return recommendationRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    public RecommendationResponseDTO buscarPorId(int id) {
        Recommendation rec = recommendationRepository.findById(id).orElse(null);
        if (rec == null) {
            logger.warn("Recomendación no encontrada con id: {}", id);
            return null;
        }
        return convertirAResponseDTO(rec);
    }

    public RecommendationResponseDTO guardar(RecommendationRequestDTO dto) {
        Recommendation rec = new Recommendation();
        rec.setUserId(dto.getUserId());
        rec.setCategoryId(dto.getCategoryId());
        rec.setContentType(dto.getContentType());
        rec.setContentId(dto.getContentId());
        rec.setReason(dto.getReason());
        rec.setScore(dto.getScore());
        rec.setGeneratedAt(dto.getGeneratedAt());
        Recommendation guardada = recommendationRepository.save(rec);
        logger.info("Recomendación creada para usuario: {}", dto.getUserId());
        return convertirAResponseDTO(guardada);
    }

    public RecommendationResponseDTO actualizar(int id, RecommendationRequestDTO dto) {
        Recommendation existente = recommendationRepository.findById(id).orElse(null);
        if (existente == null) {
            logger.warn("Recomendación no encontrada para actualizar, id: {}", id);
            return null;
        }
        existente.setUserId(dto.getUserId());
        existente.setCategoryId(dto.getCategoryId());
        existente.setContentType(dto.getContentType());
        existente.setContentId(dto.getContentId());
        existente.setReason(dto.getReason());
        existente.setScore(dto.getScore());
        existente.setGeneratedAt(dto.getGeneratedAt());
        logger.info("Recomendación actualizada con id: {}", id);
        return convertirAResponseDTO(recommendationRepository.save(existente));
    }

    public boolean eliminar(int id) {
        if (!recommendationRepository.existsById(id)) {
            logger.warn("Recomendación no encontrada para eliminar, id: {}", id);
            return false;
        }
        recommendationRepository.deleteById(id);
        logger.info("Recomendación eliminada con id: {}", id);
        return true;
    }

    private RecommendationResponseDTO convertirAResponseDTO(Recommendation r) {
        return new RecommendationResponseDTO(
                r.getId(), r.getUserId(), r.getCategoryId(), r.getContentType(),
                r.getContentId(), r.getReason(), r.getScore(), r.getGeneratedAt()
        );
    }
}
    