package com.onlyfy.search_service.service;

import com.onlyfy.search_service.client.MusicCategoryClient;
import com.onlyfy.search_service.dto.MusicCategoryResponseDTO;
import com.onlyfy.search_service.dto.SearchRequestDTO;
import com.onlyfy.search_service.dto.SearchResponseDTO;
import com.onlyfy.search_service.model.SearchRecord;
import com.onlyfy.search_service.repository.SearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    private final SearchRepository searchRepository;
    private final MusicCategoryClient musicCategoryClient;

    public SearchService(SearchRepository searchRepository,
                         MusicCategoryClient musicCategoryClient) {
        this.searchRepository = searchRepository;
        this.musicCategoryClient = musicCategoryClient;
    }

    public SearchResponseDTO guardar(SearchRequestDTO dto) {
        MusicCategoryResponseDTO categoria;
        try {
            categoria = musicCategoryClient.getCategoryById(
                Integer.parseInt(dto.getCategoryId())
            );
        } catch (Exception e) {
            logger.error("No se pudo consultar music-category-service: {}", e.getMessage());
            throw new RuntimeException("No se pudo consultar el microservicio de categorías");
        }

        if (!categoria.isActive()) {
            throw new RuntimeException("La categoría no está activa");
        }

        SearchRecord record = new SearchRecord();
        record.setUserId(dto.getUserId());
        record.setQuery(dto.getQuery());
        record.setCategoryId(dto.getCategoryId());
        record.setContentType(dto.getContentType());
        record.setSearchDate(dto.getSearchDate());
        SearchRecord guardado = searchRepository.save(record);
        logger.info("Búsqueda registrada para usuario: {}", dto.getUserId());
        return convertirAResponseDTO(guardado);
    }

    public List<SearchResponseDTO> listar() {
        logger.info("Listando todas las búsquedas");
        return searchRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    public SearchResponseDTO buscarPorId(int id) {
        SearchRecord record = searchRepository.findById(id).orElse(null);
        if (record == null) {
            logger.warn("Búsqueda no encontrada con id: {}", id);
            return null;
        }
        return convertirAResponseDTO(record);
    }

    public boolean eliminar(int id) {
        if (!searchRepository.existsById(id)) {
            logger.warn("Búsqueda no encontrada para eliminar, id: {}", id);
            return false;
        }
        searchRepository.deleteById(id);
        logger.info("Búsqueda eliminada con id: {}", id);
        return true;
    }

    private SearchResponseDTO convertirAResponseDTO(SearchRecord r) {
        return new SearchResponseDTO(
                r.getId(), r.getUserId(), r.getQuery(),
                r.getCategoryId(), r.getContentType(), r.getSearchDate()
        );
    }
}