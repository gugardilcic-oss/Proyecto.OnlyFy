package com.onlyfy.recomenations.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RecomendationRequestDTO {

    @NotBlank(message = "El nombre de la canción es obligatorio")
    private String nombreCancion;

    @NotBlank(message = "El nombre del artista es obligatorio")
    private String artistas;

    @NotBlank(message = "El álbum es obligatorio")
    private String album;

    @NotBlank(message = "El género es obligatorio")
    private String genero;

    @Min(value = 1, message = "La duración debe ser mayor a cero")
    private int duracion;

    // Getters y Setters
    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public String getArtistas() {
        return artistas;
    }

    public void setArtistas(String artistas) {
        this.artistas = artistas;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}