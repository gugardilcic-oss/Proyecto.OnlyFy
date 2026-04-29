package com.onlyfy.user_perfil.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_perfil")

public class UserPerfilModel {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(nullable = false)
private String nombre;

@Column(nullable = false)
private int edad;

@Column(nullable = false)
private String email;

@Column(nullable = false)
private int telefono;

@Column(nullable = false)
private String artistaMasEscuchado;

@Column(nullable = false)
private String generoMusicalPreferido;

@Column(nullable = false)
private String cancionMasEscuchada;

@Column(nullable = false)
private String albumMasEscuchado;

@Column(nullable = false)
private String playlistMasEscuchada;

public UserPerfilModel(int id, String nombre, int edad, String email, int telefono, String artistaMasEscuchado,
        String generoMusicalPreferido, String cancionMasEscuchada, String albumMasEscuchado,
        String playlistMasEscuchada) {
    this.id = id;
    this.nombre = nombre;
    this.edad = edad;
    this.email = email;
    this.telefono = telefono;
    this.artistaMasEscuchado = artistaMasEscuchado;
    this.generoMusicalPreferido = generoMusicalPreferido;
    this.cancionMasEscuchada = cancionMasEscuchada;
    this.albumMasEscuchado = albumMasEscuchado;
    this.playlistMasEscuchada = playlistMasEscuchada;
}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    public String getArtistaMasEscuchado() {
        return artistaMasEscuchado;
    }
    public void setArtistaMasEscuchado(String artistaMasEscuchado) {
        this.artistaMasEscuchado = artistaMasEscuchado;
    }
    public String getGeneroMusicalPreferido() {
        return generoMusicalPreferido;
    }
    public void setGeneroMusicalPreferido(String generoMusicalPreferido) {
        this.generoMusicalPreferido = generoMusicalPreferido;
    }
    public String getCancionMasEscuchada() {
        return cancionMasEscuchada;
    }
    public void setCancionMasEscuchada(String cancionMasEscuchada) {
        this.cancionMasEscuchada = cancionMasEscuchada;
    }
    public String getAlbumMasEscuchado() {
        return albumMasEscuchado;
    }
    public void setAlbumMasEscuchado(String albumMasEscuchado) {
        this.albumMasEscuchado = albumMasEscuchado;
    }
    public String getPlaylistMasEscuchada() {
        return playlistMasEscuchada;
    }
    public void setPlaylistMasEscuchada(String playlistMasEscuchada) {
        this.playlistMasEscuchada = playlistMasEscuchada;
    }

}