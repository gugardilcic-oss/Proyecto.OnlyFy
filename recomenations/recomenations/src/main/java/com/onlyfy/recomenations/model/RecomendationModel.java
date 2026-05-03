package com.onlyfy.recomenations.model;
import jakarta.persistence.*;

                         
@Entity
@Table(name = "recomendations")

public class RecomendationModel {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
private String nombreCancion;

@Column(nullable = false)
private String artistas;

@Column(nullable = false)
private String album;

@Column(nullable = false)
private String genero;

@Column(nullable = false)
private int duracion; // Duración en segundos

//constructor con y sin parametros
public RecomendationModel( String nombreCancion, String artistas, String album, String genero, int duracion) {
    this.nombreCancion = nombreCancion;
    this.artistas = artistas;
    this.album = album;
    this.genero = genero;
    this.duracion = duracion;
}
public RecomendationModel() {

}
public Long getId() {
    return id;
}
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
