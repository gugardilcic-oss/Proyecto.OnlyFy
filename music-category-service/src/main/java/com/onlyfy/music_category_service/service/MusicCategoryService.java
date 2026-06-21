package com.onlyfy.music_category_service.service;

import com.onlyfy.music_category_service.dto.MusicCategoryRequestDTO;
import com.onlyfy.music_category_service.dto.MusicCategoryResponseDTO;
import com.onlyfy.music_category_service.model.MusicCategory;
import com.onlyfy.music_category_service.repository.MusicCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(MusicCategoryService.class);

    private final MusicCategoryRepository musicCategoryRepository;

    public MusicCategoryService(MusicCategoryRepository musicCategoryRepository) {
        this.musicCategoryRepository = musicCategoryRepository;
    }

    public List<MusicCategoryResponseDTO> listar() {
        logger.info("Listando todas las categorías musicales");
        return musicCategoryRepository.findAll()
                .stream()
                .map(this::convertirAResponseDTO)
                .collect(Collectors.toList());
    }

    public MusicCategoryResponseDTO buscarPorId(int id) {
        MusicCategory category = musicCategoryRepository.findById(id).orElse(null);
        if (category == null) {
            logger.warn("Categoría no encontrada con id: {}", id);
            return null;
        }
        return convertirAResponseDTO(category);
    }

    public MusicCategoryResponseDTO guardar(MusicCategoryRequestDTO dto) {
        MusicCategory category = new MusicCategory();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setGenre(dto.getGenre());
        category.setImageUrl(dto.getImageUrl());
        category.setActive(dto.isActive());
        MusicCategory guardada = musicCategoryRepository.save(category);
        logger.info("Categoría musical creada: {}", dto.getName());
        return convertirAResponseDTO(guardada);
    }

    public MusicCategoryResponseDTO actualizar(int id, MusicCategoryRequestDTO dto) {
        MusicCategory existente = musicCategoryRepository.findById(id).orElse(null);
        if (existente == null) {
            logger.warn("Categoría no encontrada para actualizar, id: {}", id);
            return null;
        }
        existente.setName(dto.getName());
        existente.setDescription(dto.getDescription());
        existente.setGenre(dto.getGenre());
        existente.setImageUrl(dto.getImageUrl());
        existente.setActive(dto.isActive());
        logger.info("Categoría musical actualizada con id: {}", id);
        return convertirAResponseDTO(musicCategoryRepository.save(existente));
    }

    public boolean eliminar(int id) {
        if (!musicCategoryRepository.existsById(id)) {
            logger.warn("Categoría no encontrada para eliminar, id: {}", id);
            return false;
        }
        musicCategoryRepository.deleteById(id);
        logger.info("Categoría musical eliminada con id: {}", id);
        return true;
    }

    private MusicCategoryResponseDTO convertirAResponseDTO(MusicCategory c) {
        return new MusicCategoryResponseDTO(
                c.getId(), c.getName(), c.getDescription(),
                c.getGenre(), c.getImageUrl(), c.isActive()
        );
    }
}