package com.onlyfy.recommendation_service.controller;

import com.onlyfy.recommendation_service.dto.RecommendationRequestDTO;
import com.onlyfy.recommendation_service.dto.RecommendationResponseDTO;
import com.onlyfy.recommendation_service.service.RecommendationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping
    public ResponseEntity<List<RecommendationResponseDTO>> listar() {
        return ResponseEntity.ok(recommendationService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        RecommendationResponseDTO rec = recommendationService.buscarPorId(id);
        if (rec == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recomendación no encontrada");
        }
        return ResponseEntity.ok(rec);
    }

    @PostMapping
    public ResponseEntity<RecommendationResponseDTO> guardar(
            @Valid @RequestBody RecommendationRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recommendationService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id,
                                         @Valid @RequestBody RecommendationRequestDTO dto) {
        RecommendationResponseDTO actualizada = recommendationService.actualizar(id, dto);
        if (actualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recomendación no encontrada");
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (!recommendationService.eliminar(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recomendación no encontrada");
        }
        return ResponseEntity.noContent().build();
    }
}