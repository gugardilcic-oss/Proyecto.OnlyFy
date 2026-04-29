package com.onlyfy.user_perfil.controller;

import com.onlyfy.user_perfil.model.UserPerfilModel;
import com.onlyfy.user_perfil.service.UserPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles") // Esta será la URL base
public class UserPerfilController {

    @Autowired
    private UserPerfilService userPerfilService;

    // Obtener todos los perfiles
    @GetMapping
    public List<UserPerfilModel> obtenerTodos() {
        return userPerfilService.listarTodos();
    }

    // Guardar un nuevo perfil
    @PostMapping
    public UserPerfilModel guardar(@RequestBody UserPerfilModel perfil) {
        return userPerfilService.crear(perfil);
    }
}