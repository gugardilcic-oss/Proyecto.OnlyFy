package com.onlyfy.login_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Imports de tus clases corregidos
import com.onlyfy.login_service.model.InicioSesionModel;
import com.onlyfy.login_service.service.InicioSesionService;

@RestController
@RequestMapping("/auth") // Ruta base para este controlador
public class InicioSesionController {

    @Autowired
    private InicioSesionService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody InicioSesionModel loginRequest) {
        // Llamamos al servicio para validar
        InicioSesionModel usuario = service.validarLogin(
            loginRequest.getNombreUsuario(),
            loginRequest.getContraseña()
        );

        if (usuario != null) {
            // Si es correcto, devolvemos el objeto y un estado 200 OK
            return ResponseEntity.ok(usuario);
        } else {
            // Si es incorrecto, devolvemos un error 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
        }
    }
}