package com.onlyfy.subscription_service.service;

import com.onlyfy.subscription_service.model.Subscription;
import com.onlyfy.subscription_service.repository.SubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public List<Subscription> listarSuscripciones() {
        logger.info("Listando todas las suscripciones");
        return subscriptionRepository.findAll();
    }

    public Optional<Subscription> obtenerPorId(int id) {
        logger.info("Buscando suscripción con id: {}", id);
        return subscriptionRepository.findById(id);
    }

    public Subscription crearSuscripcion(Subscription subscription) {
        logger.info("Creando suscripción para usuario: {}", subscription.getUserId());
        return subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> actualizarSuscripcion(int id, Subscription actualizada) {
        return subscriptionRepository.findById(id).map(sub -> {
            sub.setUserId(actualizada.getUserId());
            sub.setPlan(actualizada.getPlan());
            sub.setStatus(actualizada.getStatus());
            sub.setStartDate(actualizada.getStartDate());
            sub.setEndDate(actualizada.getEndDate());
            sub.setPrice(actualizada.getPrice());
            sub.setAutoRenew(actualizada.isAutoRenew());
            logger.info("Suscripción actualizada con id: {}", id);
            return subscriptionRepository.save(sub);
        });
    }

    public boolean eliminarSuscripcion(int id) {
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
            logger.info("Suscripción eliminada con id: {}", id);
            return true;
        }
        logger.warn("Suscripción no encontrada para eliminar, id: {}", id);
        return false;
    }

    public boolean datosValidos(Subscription subscription) {
        if (subscription.getUserId() == null || subscription.getUserId().isBlank()) {
            logger.warn("Validación fallida: userId vacío");
            return false;
        }
        if (subscription.getPlan() == null || subscription.getPlan().isBlank()) {
            logger.warn("Validación fallida: plan vacío");
            return false;
        }
        if (subscription.getStatus() == null || subscription.getStatus().isBlank()) {
            logger.warn("Validación fallida: status vacío");
            return false;
        }
        if (subscription.getStartDate() == null) {
            logger.warn("Validación fallida: startDate nulo");
            return false;
        }
        if (subscription.getEndDate() == null) {
            logger.warn("Validación fallida: endDate nulo");
            return false;
        }
        if (subscription.getPrice() < 0) {
            logger.warn("Validación fallida: price inválido");
            return false;
        }
        return true;
    }
}