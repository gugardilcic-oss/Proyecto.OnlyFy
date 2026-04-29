package com.onlyfy.login_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlyfy.login_service.model.InicioSesionModel;
import com.onlyfy.login_service.repository.InicioSesionRepository;

@Service
public class InicioSesionService {

    @Autowired
    private InicioSesionRepository repository;

    /**
     * Lógica para validar el login
     * @return El objeto usuario si es exitoso, null si los datos son incorrectos.
     */
    public InicioSesionModel validarLogin(String usuario, String contrasena) {
        // 1. Buscamos al usuario por su nombre
        InicioSesionModel usuarioEncontrado = repository.findByNombreUsuario(usuario);

        // 2. Verificamos si existe y si la contraseña coincide
        if (usuarioEncontrado != null && usuarioEncontrado.getContraseña().equals(contrasena)) {
            return usuarioEncontrado;
        }

        // 3. Si algo falla, retornamos null
        return null;
    }
}