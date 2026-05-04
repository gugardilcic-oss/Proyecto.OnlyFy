package com.onlyfy.recommendation_service.dto;

import java.time.LocalDateTime;

public class RecommendationResponseDTO {

    private int id;
    private String userId;
    private String categoryId;
    private String contentType;
    private String contentId;
    private String reason;
    private double score;
    private LocalDateTime generatedAt;

    public RecommendationResponseDTO() {}

    public RecommendationResponseDTO(int id, String userId, String categoryId,
                                      String contentType, String contentId,
                                      String reason, double score,
                                      LocalDateTime generatedAt) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.contentType = contentType;
        this.contentId = contentId;
        this.reason = reason;
        this.score = score;
        this.generatedAt = generatedAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

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