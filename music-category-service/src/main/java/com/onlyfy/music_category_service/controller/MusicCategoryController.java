package com.onlyfy.music_category_service.controller;

import com.onlyfy.music_category_service.dto.MusicCategoryRequestDTO;
import com.onlyfy.music_category_service.dto.MusicCategoryResponseDTO;
import com.onlyfy.music_category_service.service.MusicCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
// ESTOS TRES SON LOS QUE NECESITAN QUE MAVEN RECARGUE CORRECTAMENTE:
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
@Tag(name = "Categorías Musicales", description = "Controlador principal para gestionar las categorías y géneros de música")
@RestController
@RequestMapping("/api/v1/music-categories")
public class MusicCategoryController {

    private final MusicCategoryService musicCategoryService;

    public MusicCategoryController(MusicCategoryService musicCategoryService) {
        this.musicCategoryService = musicCategoryService;
    }

    @Operation(summary = "Listar todas las categorías", description = "Retorna un listado completo con todas las categorías musicales registradas.")
    @ApiResponse(responseCode = "200", description = "Operación realizada con éxito")
    @GetMapping
    public ResponseEntity<List<MusicCategoryResponseDTO>> listar() {
        return ResponseEntity.ok(musicCategoryService.listar());
    }

    @Operation(summary = "Buscar categoría por ID", description = "Obtiene los detalles de una categoría e incluye hipermedios (HATEOAS) para su navegación.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría encontrada exitosamente"),
        @ApiResponse(responseCode = "404", description = "La categoría solicitada no existe en el sistema")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @Parameter(description = "ID numérico de la categoría musical", example = "1")
            @PathVariable int id) {
        
        MusicCategoryResponseDTO category = musicCategoryService.buscarPorId(id);
        if (category == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada");
        }

        // --- APLICANDO HATEOAS ---
        // Envolvemos el DTO en un EntityModel para poder inyectarle enlaces navegables
        EntityModel<MusicCategoryResponseDTO> recurso = EntityModel.of(category);

        // Enlace 'self': Vincula directamente a este mismo endpoint con su ID
        recurso.add(linkTo(methodOn(MusicCategoryController.class).buscarPorId(id)).withSelfRel());

        // Enlace 'categorias': Proporciona al cliente la URL para volver al listado general
        recurso.add(linkTo(methodOn(MusicCategoryController.class).listar()).withRel("categorias"));

        return ResponseEntity.ok(recurso);
    }

    @Operation(summary = "Crear una nueva categoría", description = "Registra una categoría musical validando que los parámetros obligatorios estén presentes.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Categoría creada con éxito"),
        @ApiResponse(responseCode = "400", description = "Petición inválida por fallas en las validaciones de datos")
    })
    @PostMapping
    public ResponseEntity<MusicCategoryResponseDTO> guardar(
            @Valid @RequestBody MusicCategoryRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(musicCategoryService.guardar(dto));
    }

    @Operation(summary = "Actualizar una categoría existente", description = "Modifica los atributos de una categoría musical basándose en su ID único.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categoría actualizada de forma correcta"),
        @ApiResponse(responseCode = "404", description = "No se pudo actualizar porque el ID no existe")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @Parameter(description = "ID de la categoría a modificar", example = "1")
            @PathVariable int id,
            @Valid @RequestBody MusicCategoryRequestDTO dto) {
        
        MusicCategoryResponseDTO actualizada = musicCategoryService.actualizar(id, dto);
        if (actualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada");
        }
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Eliminar una categoría", description = "Remueve físicamente el registro de la categoría musical mediante su identificador.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "24", description = "Categoría eliminada con éxito (Sin contenido en respuesta)"),
        @ApiResponse(responseCode = "404", description = "No se encontró ninguna categoría con el ID proveído")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(
            @Parameter(description = "ID de la categoría a eliminar", example = "1")
            @PathVariable int id) {
        
        if (!musicCategoryService.eliminar(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada");
        }
        return ResponseEntity.noContent().build();
    }
}