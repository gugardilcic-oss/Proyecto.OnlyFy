package com.onlyfy.notification_service.controller;

import com.onlyfy.notification_service.model.Notification;
import com.onlyfy.notification_service.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public ResponseEntity<List<Notification>> obtenerNotificaciones() {
        return ResponseEntity.ok(notificationService.listarNotificaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable int id) {
        return notificationService.obtenerPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Notificación no encontrada con id: {}", id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Notificación no encontrada");
                });
    }

    @PostMapping
    public ResponseEntity<?> crearNotificacion(@RequestBody Notification notification) {
        if (!notificationService.datosValidos(notification)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Datos de la notificación inválidos");
        }
        Notification nueva = notificationService.crearNotificacion(notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarNotificacion(@PathVariable int id,
                                                     @RequestBody Notification notification) {
        if (!notificationService.datosValidos(notification)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Datos de la notificación inválidos");
        }
        return notificationService.actualizarNotificacion(id, notification)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Notificación no encontrada"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarNotificacion(@PathVariable int id) {
        if (notificationService.eliminarNotificacion(id)) {
            return ResponseEntity.ok("Notificación eliminada correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notificación no encontrada");
    }
}