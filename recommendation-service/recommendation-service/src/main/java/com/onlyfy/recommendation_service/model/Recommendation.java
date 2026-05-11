package com.onlyfy.recommendation_service.model     ;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String categoryId;

    @Column(nullable = false)
    private String contentType; // SONG, PLAYLIST, ARTIST, JAM

    @Column(nullable = false)
    private String contentId;

    @Column(nullable = false)
    private String reason; // BASED_ON_HISTORY, POPULAR, SUBSCRIPTION

    @Column(nullable = false)
    private double score;

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    public Recommendation() {}

    public Recommendation(int id, String userId, String categoryId, String contentType,
                          String contentId, String reason, double score,
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