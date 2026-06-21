package com.onlyfy.recommendation_service.controller;

import com.onlyfy.recommendation_service.dto.RecommendationResponseDTO;
import com.onlyfy.recommendation_service.model.Recommendation;
import com.onlyfy.recommendation_service.service.RecommendationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recommendations")
@Tag(name = "Recomendaciones", description = "Endpoints interactivos para la gestión de recomendaciones de usuarios")
public class RecommendationController {

    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtener recomendaciones por ID de usuario", description = "Retorna una lista de recomendaciones envueltas en hipermedios HATEOAS")
    public ResponseEntity<List<EntityModel<RecommendationResponseDTO>>> getByUserId(@PathVariable Long userId) {
        List<Recommendation> recommendations = service.obtenerPorUsuario(userId);

        List<EntityModel<RecommendationResponseDTO>> resources = recommendations.stream()
                .map(rec -> {
                    // 1. Mapear de Entidad a DTO
                    RecommendationResponseDTO dto = convertToResponseDTO(rec);
                    
                    // 2. Crear el contenedor HATEOAS
                    EntityModel<RecommendationResponseDTO> model = EntityModel.of(dto);
                    
                    // 3. Añadir enlace dinámico al detalle individual
                    Link selfLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(RecommendationController.class).getById(dto.getId())
                    ).withSelfRel();
                    
                    model.add(selfLink);
                    return model;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalle de una recomendación", description = "Busca una recomendación por su ID único y añade enlaces de retorno")
    public ResponseEntity<EntityModel<RecommendationResponseDTO>> getById(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(rec -> {
                    RecommendationResponseDTO dto = convertToResponseDTO(rec);
                    EntityModel<RecommendationResponseDTO> model = EntityModel.of(dto);
                    
                    // HATEOAS: Enlace para volver a ver todas las recomendaciones de este usuario
                    Link userRecsLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(RecommendationController.class).getByUserId(dto.getUserId())
                    ).withRel("todos-los-aportes-usuario");
                    
                    model.add(userRecsLink);
                    return ResponseEntity.ok(model);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Método auxiliar manual para mapear la Entidad al DTO de respuesta.
     * Ajusta los sets si tus variables se llaman de otra forma en el DTO.
     */
    private RecommendationResponseDTO convertToResponseDTO(Recommendation rec) {
        RecommendationResponseDTO dto = new RecommendationResponseDTO();
        dto.setId(rec.getId());
        dto.setUserId(rec.getUserId());
        dto.setDescription(rec.getDescription());
        return dto;
    }
}