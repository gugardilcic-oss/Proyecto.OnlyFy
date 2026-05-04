package com.onlyfy.notification_service.service;

import com.onlyfy.notification_service.model.Notification;
import com.onlyfy.notification_service.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> listarNotificaciones() {
        logger.info("Listando todas las notificaciones");
        return notificationRepository.findAll();
    }

    public Optional<Notification> obtenerPorId(int id) {
        logger.info("Buscando notificación con id: {}", id);
        return notificationRepository.findById(id);
    }

    public Notification crearNotificacion(Notification notification) {
        logger.info("Creando notificación para usuario: {}", notification.getUserId());
        return notificationRepository.save(notification);
    }

    public Optional<Notification> actualizarNotificacion(int id, Notification actualizada) {
        return notificationRepository.findById(id).map(notif -> {
            notif.setUserId(actualizada.getUserId());
            notif.setTitle(actualizada.getTitle());
            notif.setMessage(actualizada.getMessage());
            notif.setType(actualizada.getType());
            notif.setRead(actualizada.isRead());
            notif.setCreatedAt(actualizada.getCreatedAt());
            logger.info("Notificación actualizada con id: {}", id);
            return notificationRepository.save(notif);
        });
    }

    public boolean eliminarNotificacion(int id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
            logger.info("Notificación eliminada con id: {}", id);
            return true;
        }
        logger.warn("Notificación no encontrada para eliminar, id: {}", id);
        return false;
    }

    public boolean datosValidos(Notification notification) {
        if (notification.getUserId() == null || notification.getUserId().isBlank()) {
            logger.warn("Validación fallida: userId vacío");
            return false;
        }
        if (notification.getTitle() == null || notification.getTitle().isBlank()) {
            logger.warn("Validación fallida: title vacío");
            return false;
        }
        if (notification.getMessage() == null || notification.getMessage().isBlank()) {
            logger.warn("Validación fallida: message vacío");
            return false;
        }
        if (notification.getType() == null || notification.getType().isBlank()) {
            logger.warn("Validación fallida: type vacío");
            return false;
        }
        if (notification.getCreatedAt() == null) {
            logger.warn("Validación fallida: createdAt nulo");
            return false;
        }
        return true;
    }
}
