package com.onlyfy.recommendation_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients; // <-- Importante

@SpringBootApplication
@EnableFeignClients // <-- Activa el escaneo de clientes Feign distribuidos
public class RecommendationServiceApplication {
    public static void main(String[] eloquence) {
        SpringApplication.run(RecommendationServiceApplication.class, eloquence);
    }
}