package com.onlyfy.message_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class MessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario", nullable = false, length = 100)
    private String nombreUsuario;

    @Column(name = "mensaje", nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "hora")
    private String hora;

    @Column(name = "ultima_vez_en_linea")
    private String ultimaVezEnLinea;

    @Column(name = "buscar_mensaje")
    private String buscarMensaje;

    public MessageModel() {
    }

    public MessageModel(String nombreUsuario, String mensaje, String fecha, String hora, String ultimaVezEnLinea, String buscarMensaje) {
        this.nombreUsuario = nombreUsuario;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.hora = hora;
        this.ultimaVezEnLinea = ultimaVezEnLinea;
        this.buscarMensaje = buscarMensaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUltimaVezEnLinea() {
        return ultimaVezEnLinea;
    }

    public void setUltimaVezEnLinea(String ultimaVezEnLinea) {
        this.ultimaVezEnLinea = ultimaVezEnLinea;
    }

    public String getBuscarMensaje() {
        return buscarMensaje;
    }

    public void setBuscarMensaje(String buscarMensaje) {
        this.buscarMensaje = buscarMensaje;
    }
}