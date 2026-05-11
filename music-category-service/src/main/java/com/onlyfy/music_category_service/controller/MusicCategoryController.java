package com.onlyfy.music_category_service.controller;

import com.onlyfy.music_category_service.dto.MusicCategoryRequestDTO;
import com.onlyfy.music_category_service.dto.MusicCategoryResponseDTO;
import com.onlyfy.music_category_service.service.MusicCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/music-categories")
public class MusicCategoryController {

    private final MusicCategoryService musicCategoryService;

    public MusicCategoryController(MusicCategoryService musicCategoryService) {
        this.musicCategoryService = musicCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<MusicCategoryResponseDTO>> listar() {
        return ResponseEntity.ok(musicCategoryService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        MusicCategoryResponseDTO category = musicCategoryService.buscarPorId(id);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada");
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<MusicCategoryResponseDTO> guardar(
            @Valid @RequestBody MusicCategoryRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(musicCategoryService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable int id,
                                         @Valid @RequestBody MusicCategoryRequestDTO dto) {
        MusicCategoryResponseDTO actualizada = musicCategoryService.actualizar(id, dto);
        if (actualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada");
        }
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (!musicCategoryService.eliminar(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada");
        }
        return ResponseEntity.noContent().build();
    }
}