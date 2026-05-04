package com.onlyfy.recommendation_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class RecommendationRequestDTO {

    @NotBlank(message = "El userId es obligatorio")
    private String userId;

    @NotBlank(message = "El categoryId es obligatorio")
    private String categoryId;

    @NotBlank(message = "El tipo de contenido es obligatorio")
    private String contentType;

    @NotBlank(message = "El contentId es obligatorio")
    private String contentId;

    @NotBlank(message = "La razón es obligatoria")
    private String reason;

    @Min(value = 0, message = "El score debe ser mayor o igual a cero")
    private double score;

    @NotNull(message = "La fecha de generación es obligatoria")
    private LocalDateTime generatedAt;

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }

    public String getContentId() { return contentId; }
    public void setContentId(String contentId) { this.contentId = contentId; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public LocalDateTime getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt) { this.generatedAt = generatedAt; }
}