package com.onlyfy.search_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo que representa el resultado devuelto tras una búsqueda exitosa")
public class SearchResponseDTO {

    @Schema(description = "Identificador único del elemento encontrado", example = "105")
    private int id;

    @Schema(description = "Título o nombre principal del resultado", example = "Creep")
    private String title;

    @Schema(description = "Nombre del artista o banda asociada", example = "Radiohead")
    private String artist;

    @Schema(description = "Categoría o tipo de elemento (song, artist, album)", example = "song")
    private String type;

    // --- CONSTRUCTORES ---
    public SearchResponseDTO() {}

    public SearchResponseDTO(int id, String title, String artist, String type) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.type = type;
    }

    // --- GETTERS Y SETTERS ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}