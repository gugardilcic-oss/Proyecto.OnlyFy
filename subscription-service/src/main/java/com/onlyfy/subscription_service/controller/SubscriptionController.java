package com.onlyfy.subscription_service.controller;

import com.onlyfy.subscription_service.model.Subscription;
import com.onlyfy.subscription_service.service.SubscriptionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> obtenerSuscripciones() {
        return ResponseEntity.ok(subscriptionService.listarSuscripciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable int id) {
        return subscriptionService.obtenerPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Suscripción no encontrada con id: {}", id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Suscripción no encontrada");
                });
    }

    @PostMapping
    public ResponseEntity<?> crearSuscripcion(@RequestBody Subscription subscription) {
        if (!subscriptionService.datosValidos(subscription)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Datos de la suscripción inválidos");
        }
        Subscription nueva = subscriptionService.crearSuscripcion(subscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarSuscripcion(@PathVariable int id,
                                                    @RequestBody Subscription subscription) {
        if (!subscriptionService.datosValidos(subscription)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Datos de la suscripción inválidos");
        }
        return subscriptionService.actualizarSuscripcion(id, subscription)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Suscripción no encontrada"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarSuscripcion(@PathVariable int id) {
        if (subscriptionService.eliminarSuscripcion(id)) {
            return ResponseEntity.ok("Suscripción eliminada correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Suscripción no encontrada");
    }
}