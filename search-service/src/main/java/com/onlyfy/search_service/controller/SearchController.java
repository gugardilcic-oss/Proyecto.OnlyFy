package com.onlyfy.search_service.controller;

import com.onlyfy.search_service.dto.SearchRequestDTO;
import com.onlyfy.search_service.dto.SearchResponseDTO;
import com.onlyfy.search_service.service.SearchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResponseEntity<List<SearchResponseDTO>> listar() {
        return ResponseEntity.ok(searchService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        SearchResponseDTO record = searchService.buscarPorId(id);
        if (record == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Búsqueda no encontrada");
        }
        return ResponseEntity.ok(record);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody SearchRequestDTO dto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(searchService.guardar(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (!searchService.eliminar(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Búsqueda no encontrada");
        }
        return ResponseEntity.noContent().build();
    }
}