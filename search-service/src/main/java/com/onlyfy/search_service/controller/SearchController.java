package com.onlyfy.search_service.controller;

import com.onlyfy.search_service.dto.SearchRequestDTO;
import com.onlyfy.search_service.dto.SearchResponseDTO;
import com.onlyfy.search_service.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Importaciones estáticas cruciales para HATEOAS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Motor de Búsquedas", description = "Controlador encargado de indexar y consultar pistas musicales, álbumes y artistas")
@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @Operation(summary = "Ejecutar una búsqueda general", description = "Filtra el catálogo musical según los criterios de palabras clave provistos en el cuerpo de la petición.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda procesada con éxito"),
        @ApiResponse(responseCode = "400", description = "Criterios de búsqueda no válidos")
    })
    @PostMapping("/query")
    public ResponseEntity<List<SearchResponseDTO>> ejecutarBusqueda(@Valid @RequestBody SearchRequestDTO dto) {
        return ResponseEntity.ok(searchService.buscar(dto));
    }

    @Operation(summary = "Obtener detalle de resultado por ID", description = "Recupera un elemento del índice de búsqueda específico e incluye hipermedios navegables de HATEOAS.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Elemento encontrado"),
        @ApiResponse(responseCode = "404", description = "El ID especificado no coincide con ningún registro indexado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(
            @Parameter(description = "ID único del elemento indexado", example = "105")
            @PathVariable int id) {

        SearchResponseDTO resultado = searchService.obtenerPorId(id);
        if (resultado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resultado no encontrado en el índice");
        }

        // --- CONSTRUCCIÓN DE HATEOAS ---
        EntityModel<SearchResponseDTO> recurso = EntityModel.of(resultado);
        
        // Enlace al propio recurso
        recurso.add(linkTo(methodOn(SearchController.class).obtenerPorId(id)).withSelfRel());
        
        // Enlace alternativo para sugerir al cliente cómo ejecutar otra consulta general
        recurso.add(linkTo(methodOn(SearchController.class).ejecutarBusqueda(new SearchRequestDTO())).withRel("nueva-busqueda"));

        return ResponseEntity.ok(recurso);
    }
}