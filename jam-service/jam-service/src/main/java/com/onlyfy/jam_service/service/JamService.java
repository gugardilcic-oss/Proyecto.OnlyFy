package com.onlyfy.jam_service.service;

import com.onlyfy.jam_service.model.JamModel;
import com.onlyfy.jam_service.repository.JamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JamService {

    @Autowired
    private JamRepository jamRepository;

    // Lógica para crear una Jam
    public JamModel crearJam(JamModel jam) {
        // Aquí podrías agregar validaciones (ej. que el código no esté repetido)
        return jamRepository.save(jam);
    }

    // Lógica para listar todas
    public List<JamModel> obtenerTodas() {
        return jamRepository.findAll();
    }
}

