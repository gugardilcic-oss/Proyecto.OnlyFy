package com.onlyfy.search_service;

import com.onlyfy.search_service.dto.SearchRequestDTO;
import com.onlyfy.search_service.dto.SearchResponseDTO;
import com.onlyfy.search_service.service.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchServiceTest {

    private SearchService searchService;

    @BeforeEach
    void setUp() {
        // Inicializamos el servicio real que trabaja en memoria
        searchService = new SearchService();
    }

    @Test
    void testBuscarConResultadosExitosos() {
        // Arrange (Preparar datos de consulta)
        SearchRequestDTO request = new SearchRequestDTO();
        request.setQuery("Radiohead");

        // Act (Ejecutar la acción del servicio)
        List<SearchResponseDTO> resultados = searchService.buscar(request);

        // Assert (Validar las hipótesis)
        assertNotNull(resultados, "La lista de resultados no debería ser nula");
        assertFalse(resultados.isEmpty(), "Debería retornar al menos un elemento");
        assertEquals(2, resultados.size(), "Deberían encontrarse exactamente 2 coincidencias para Radiohead");
        assertTrue(resultados.get(0).getArtist().equalsIgnoreCase("Radiohead"));
    }

    @Test
    void testObtenerPorIdExitoso() {
        // Act
        SearchResponseDTO resultado = searchService.obtenerPorId(101);

        // Assert
        assertNotNull(resultado, "El elemento indexado debería existir");
        assertEquals("Creep", resultado.getTitle(), "El título del ID 101 debería ser 'Creep'");
        assertEquals("song", resultado.getType());
    }

    @Test
    void testObtenerPorIdFallido() {
        // Act (Buscar un ID inexistente en el catálogo)
        SearchResponseDTO resultado = searchService.obtenerPorId(999);

        // Assert (Tu flujo retorna nulo controladamente en vez de crashear con excepciones)
        assertNull(resultado, "El resultado debería ser nulo para un ID inválido");
    }
}