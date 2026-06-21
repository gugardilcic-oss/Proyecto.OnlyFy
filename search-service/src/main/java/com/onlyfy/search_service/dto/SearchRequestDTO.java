package com.onlyfy.search_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Modelo de transferencia de datos para realizar una búsqueda de música")
public class SearchRequestDTO {

    @Schema(description = "Término o palabra clave a buscar (ej: artista, canción o álbum)", example = "Radiohead")
    @NotBlank(message = "El término de búsqueda no puede estar vacío")
    private String query;

    @Schema(description = "Filtro opcional para limitar el tipo de resultado", example = "artist")
    private String filterType;

    // --- GETTERS Y SETTERS ---
    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }

    public String getFilterType() { return filterType; }
    public void setFilterType(String filterType) { this.filterType = filterType; }
}