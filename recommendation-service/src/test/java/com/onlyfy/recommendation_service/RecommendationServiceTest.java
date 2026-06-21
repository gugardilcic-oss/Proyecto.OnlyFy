package com.onlyfy.recommendation_service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.onlyfy.recommendation_service.model.Recommendation;
import com.onlyfy.recommendation_service.repository.RecommendationRepository;
import com.onlyfy.recommendation_service.service.RecommendationService; // Archivo clave importado aquí

@ExtendWith(MockitoExtension.class)
class RecommendationServiceTest {

    @Mock
    private RecommendationRepository recommendationRepository;

    @InjectMocks
    private RecommendationService recommendationService;

    private Recommendation recommendationSample;

    @BeforeEach
    void setUp() {
        recommendationSample = new Recommendation();
        recommendationSample.setId(1L);
        recommendationSample.setUserId(456L);
        recommendationSample.setDescription("Contenido recomendado de prueba");
    }

    @Test
    void testObtenerPorUsuario_Exitoso() {
        // Arrange
        Long userId = 456L;
        when(recommendationRepository.findByUserId(userId))
            .thenReturn(Arrays.asList(recommendationSample));

        // Act
        List<Recommendation> resultado = recommendationService.obtenerPorUsuario(userId);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Contenido recomendado de prueba", resultado.get(0).getDescription());
        verify(recommendationRepository, times(1)).findByUserId(userId);
    }

    @Test
    void testObtenerPorId_Encontrado() {
        // Arrange
        Long id = 1L;
        when(recommendationRepository.findById(id))
            .thenReturn(Optional.of(recommendationSample));

        // Act
        Optional<Recommendation> resultado = recommendationService.obtenerPorId(id);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(id, resultado.get().getId());
        verify(recommendationRepository, times(1)).findById(id);
    }
}