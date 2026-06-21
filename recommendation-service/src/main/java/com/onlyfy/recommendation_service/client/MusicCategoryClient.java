package com.onlyfy.recommendation_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "music-category-service", url = "http://localhost:8081/api/categories")
public interface MusicCategoryClient {

    @GetMapping("/{id}")
    Object obtenerCategoriaPorId(@PathVariable("id") Long id);
}