package com.onlyfy.search_service.dto;

import java.time.LocalDateTime;

public class SearchResponseDTO {

    private int id;
    private String userId;
    private String query;
    private String categoryId;
    private String contentType;
    private LocalDateTime searchDate;

    public SearchResponseDTO() {}

    public SearchResponseDTO(int id, String userId, String query, String categoryId,
                              String contentType, LocalDateTime searchDate) {
        this.id = id;
        this.userId = userId;
        this.query = query;
        this.categoryId = categoryId;
        this.contentType = contentType;
        this.searchDate = searchDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }

    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }

    public LocalDateTime getSearchDate() { return searchDate; }
    public void setSearchDate(LocalDateTime searchDate) { this.searchDate = searchDate; }
}