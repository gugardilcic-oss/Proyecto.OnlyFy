package com.onlyfy.search_service.controller;

import com.onlyfy.search_service.model.SearchRecord;
import com.onlyfy.search_service.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResponseEntity<List<SearchRecord>> obtenerBusquedas() {
        return ResponseEntity.ok(searchService.listarBusquedas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable int id) {
        return searchService.obtenerPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Búsqueda no encontrada con id: {}", id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Búsqueda no encontrada");
                });
    }

    @PostMapping
    public ResponseEntity<?> crearBusqueda(@RequestBody SearchRecord record) {
        if (!searchService.datosValidos(record)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Datos de búsqueda inválidos");
        }
        SearchRecord nuevo = searchService.crearBusqueda(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarBusqueda(@PathVariable int id,
                                                 @RequestBody SearchRecord record) {
        if (!searchService.datosValidos(record)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Datos de búsqueda inválidos");
        }
        return searchService.actualizarBusqueda(id, record)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Búsqueda no encontrada"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarBusqueda(@PathVariable int id) {
        if (searchService.eliminarBusqueda(id)) {
            return ResponseEntity.ok("Búsqueda eliminada correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Búsqueda no encontrada");
    }
}