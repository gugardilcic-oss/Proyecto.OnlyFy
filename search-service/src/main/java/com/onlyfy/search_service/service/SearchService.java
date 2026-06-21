package com.onlyfy.search_service.service;

import com.onlyfy.search_service.dto.SearchRequestDTO;
import com.onlyfy.search_service.dto.SearchResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);
    private final List<SearchResponseDTO> baseDeDatosSimulada = new ArrayList<>();

    public SearchService() {
        // Inicializamos algunos datos de prueba indexados
        baseDeDatosSimulada.add(new SearchResponseDTO(101, "Creep", "Radiohead", "song"));
        baseDeDatosSimulada.add(new SearchResponseDTO(102, "Karma Police", "Radiohead", "song"));
        baseDeDatosSimulada.add(new SearchResponseDTO(103, "Starboy", "The Weeknd", "song"));
    }

    public List<SearchResponseDTO> buscar(SearchRequestDTO dto) {
        logger.info("Ejecutando consulta de búsqueda para el término: '{}'", dto.getQuery());
        if (dto.getQuery() == null || dto.getQuery().isBlank()) {
            return baseDeDatosSimulada;
        }
        return baseDeDatosSimulada.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(dto.getQuery().toLowerCase()) ||
                                item.getArtist().toLowerCase().contains(dto.getQuery().toLowerCase()))
                .collect(Collectors.toList());
    }

    public SearchResponseDTO obtenerPorId(int id) {
        logger.info("Buscando elemento indexado con ID: {}", id);
        return baseDeDatosSimulada.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }
}