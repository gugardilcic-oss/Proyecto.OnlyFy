package com.onlyfy.recommendation_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RecommendationRequestDTO {
    @NotNull(message = "El ID de usuario es mandatorio")
    private Long userId;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    // Getters y Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}