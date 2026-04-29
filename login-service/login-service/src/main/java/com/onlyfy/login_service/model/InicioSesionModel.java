package com.onlyfy.login_service.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity 

@Table(name = "usuarios")

public class InicioSesionModel {

    @Column(name = "NombreUsuario", nullable = false, unique = true)
    private String NombreUsuario;
    @Column(name = "Contraseña", nullable = false)
    private String Contraseña;

//constructor con parametros
public InicioSesionModel(String nombreUsuario, String contraseña) {
    this.NombreUsuario = nombreUsuario;
    this.Contraseña = contraseña;
}

public String getNombreUsuario() {
    return NombreUsuario;
}
public String getContraseña() {
    return Contraseña;
}
public void setNombreUsuario(String nombreUsuario) {
    NombreUsuario = nombreUsuario;
}
public void setContraseña(String contraseña) {
    Contraseña = contraseña;
}

}
