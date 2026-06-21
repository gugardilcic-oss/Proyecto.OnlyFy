package com.onlyfy.music_category_service.service;

import com.onlyfy.music_category_service.dto.MusicCategoryRequestDTO;
import com.onlyfy.music_category_service.dto.MusicCategoryResponseDTO;
import com.onlyfy.music_category_service.model.MusicCategory;
import com.onlyfy.music_category_service.repository.MusicCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MusicCategoryServiceTest {

    @Mock
    private MusicCategoryRepository musicCategoryRepository;

    @InjectMocks
    private MusicCategoryService musicCategoryService;

    private MusicCategory categoriaMock;

    @BeforeEach
    void setup() {
        // Inicializamos una entidad base para reutilizar en los escenarios de simulación
        categoriaMock = new MusicCategory();
        categoriaMock.setId(1);
        categoriaMock.setName("Indie Rock");
        categoriaMock.setDescription("Bandas independientes con guitarras melódicas");
        categoriaMock.setGenre("Rock");
        categoriaMock.setImageUrl("https://images.onlyfy.com/indie.png");
        categoriaMock.setActive(true);
    }

    @Test
    @DisplayName("Test 1: Buscar por ID exitoso cuando el registro existe")
    void debeRetornarCategoriaResponseDTOCuandoIdExiste() {
        // Given (Dado el escenario donde el repositorio encuentra el registro)
        when(musicCategoryRepository.findById(1)).thenReturn(Optional.of(categoriaMock));

        // When (Cuando se ejecuta la lógica del servicio)
        MusicCategoryResponseDTO resultado = musicCategoryService.buscarPorId(1);

        // Then (Entonces se comprueban los resultados esperados)
        assertNotNull(resultado, "El resultado no debería ser nulo");
        assertEquals(1, resultado.getId());
        assertEquals("Indie Rock", resultado.getName());
        assertEquals("Rock", resultado.getGenre());
        
        // Verificamos que el repositorio fue consultado exactamente 1 vez
        verify(musicCategoryRepository, times(1)).findById(1);
    }

    @Test
    @DisplayName("Test 2: Buscar por ID retorna nulo cuando el registro no existe")
    void debeRetornarNullCuandoIdNoExiste() {
        // Given (Dado que el repositorio no encuentra nada para el ID provisto)
        when(musicCategoryRepository.findById(99)).thenReturn(Optional.empty());

        // When (Cuando se ejecuta la consulta)
        MusicCategoryResponseDTO resultado = musicCategoryService.buscarPorId(99);

        // Then (Entonces la respuesta debe ser controlada de forma segura)
        assertNull(resultado, "Debería retornar null al no existir en base de datos");
        verify(musicCategoryRepository, times(1)).findById(99);
    }

    @Test
    @DisplayName("Test 3: Guardar una nueva categoría mapea y persiste los campos correctamente")
    void debeGuardarYRetornarCategoriaFormateada() {
        // Given (Preparación del DTO entrante y configuración del simulador)
        MusicCategoryRequestDTO requestDTO = new MusicCategoryRequestDTO();
        requestDTO.setName("Synthwave");
        requestDTO.setDescription("Música electrónica retro de los 80s");
        requestDTO.setGenre("Electrónica");
        requestDTO.setImageUrl("https://images.onlyfy.com/synth.png");
        requestDTO.setActive(true);

        MusicCategory categoriaGuardada = new MusicCategory();
        categoriaGuardada.setId(5); // Simulamos que la base de datos autoincrementó el ID a 5
        categoriaGuardada.setName(requestDTO.getName());
        categoriaGuardada.setDescription(requestDTO.getDescription());
        categoriaGuardada.setGenre(requestDTO.getGenre());
        categoriaGuardada.setImageUrl(requestDTO.getImageUrl());
        categoriaGuardada.setActive(requestDTO.isActive());

        // Simulamos que al llamar a .save() con cualquier objeto MusicCategory, devuelva la entidad con su ID asignado
        when(musicCategoryRepository.save(any(MusicCategory.class))).thenReturn(categoriaGuardada);

        // When (Cuando enviamos la orden de guardar)
        MusicCategoryResponseDTO resultado = musicCategoryService.guardar(requestDTO);

        // Then (Validamos las aserciones lógicas del DTO de salida)
        assertNotNull(resultado);
        assertEquals(5, resultado.getId());
        assertEquals("Synthwave", resultado.getName());
        assertTrue(resultado.isActive());
        verify(musicCategoryRepository, times(1)).save(any(MusicCategory.class));
    }
}