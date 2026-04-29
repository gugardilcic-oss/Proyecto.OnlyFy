package com.onlyfy.search_service.service;

import com.onlyfy.search_service.model.SearchRecord;
import com.onlyfy.search_service.repository.SearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    private final SearchRepository searchRepository;

    public SearchService(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    public List<SearchRecord> listarBusquedas() {
        logger.info("Listando todas las búsquedas");
        return searchRepository.findAll();
    }

    public Optional<SearchRecord> obtenerPorId(int id) {
        logger.info("Buscando registro con id: {}", id);
        return searchRepository.findById(id);
    }

    public SearchRecord crearBusqueda(SearchRecord record) {
        logger.info("Registrando búsqueda del usuario: {}", record.getUserId());
        return searchRepository.save(record);
    }

    public Optional<SearchRecord> actualizarBusqueda(int id, SearchRecord actualizado) {
        return searchRepository.findById(id).map(record -> {
            record.setUserId(actualizado.getUserId());
            record.setQuery(actualizado.getQuery());
            record.setCategory(actualizado.getCategory());
            record.setResultsCount(actualizado.getResultsCount());
            record.setSearchDate(actualizado.getSearchDate());
            logger.info("Búsqueda actualizada con id: {}", id);
            return searchRepository.save(record);
        });
    }

    public boolean eliminarBusqueda(int id) {
        if (searchRepository.existsById(id)) {
            searchRepository.deleteById(id);
            logger.info("Búsqueda eliminada con id: {}", id);
            return true;
        }
        logger.warn("Búsqueda no encontrada para eliminar, id: {}", id);
        return false;
    }

    public boolean datosValidos(SearchRecord record) {
        if (record.getUserId() == null || record.getUserId().isBlank()) {
            logger.warn("Validación fallida: userId vacío");
            return false;
        }
        if (record.getQuery() == null || record.getQuery().isBlank()) {
            logger.warn("Validación fallida: query vacío");
            return false;
        }
        if (record.getCategory() == null || record.getCategory().isBlank()) {
            logger.warn("Validación fallida: category vacía");
            return false;
        }
        if (record.getResultsCount() < 0) {
            logger.warn("Validación fallida: resultsCount inválido");
            return false;
        }
        if (record.getSearchDate() == null) {
            logger.warn("Validación fallida: searchDate nulo");
            return false;
        }
        return true;
    }
}