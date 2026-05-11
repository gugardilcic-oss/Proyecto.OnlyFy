package com.onlyfy.search_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class SearchRequestDTO {

    @NotBlank(message = "El userId es obligatorio")
    private String userId;

    @NotBlank(message = "La búsqueda es obligatoria")
    private String query;

    @NotBlank(message = "El categoryId es obligatorio")
    private String categoryId;

    @NotBlank(message = "El tipo de contenido es obligatorio")
    private String contentType;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDateTime searchDate;

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