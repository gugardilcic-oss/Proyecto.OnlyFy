package com.onlyfy.payment_service.service;

import com.onlyfy.payment_service.model.Payment;
import com.onlyfy.payment_service.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> listarPagos() {
        logger.info("Listando todos los pagos");
        return paymentRepository.findAll();
    }

    public Optional<Payment> obtenerPorId(int id) {
        logger.info("Buscando pago con id: {}", id);
        return paymentRepository.findById(id);
    }

    public Payment crearPago(Payment payment) {
        logger.info("Creando pago para usuario: {}", payment.getUserId());
        return paymentRepository.save(payment);
    }

    public Optional<Payment> actualizarPago(int id, Payment paymentActualizado) {
        return paymentRepository.findById(id).map(payment -> {
            payment.setUserId(paymentActualizado.getUserId());
            payment.setAmount(paymentActualizado.getAmount());
            payment.setCurrency(paymentActualizado.getCurrency());
            payment.setMethod(paymentActualizado.getMethod());
            payment.setStatus(paymentActualizado.getStatus());
            payment.setPaymentDate(paymentActualizado.getPaymentDate());
            payment.setDescription(paymentActualizado.getDescription());
            logger.info("Pago actualizado con id: {}", id);
            return paymentRepository.save(payment);
        });
    }

    public boolean eliminarPago(int id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            logger.info("Pago eliminado con id: {}", id);
            return true;
        }
        logger.warn("Pago no encontrado para eliminar, id: {}", id);
        return false;
    }

    public boolean datosValidos(Payment payment) {
        if (payment.getUserId() == null || payment.getUserId().isBlank()) {
            logger.warn("Validación fallida: userId vacío");
            return false;
        }
        if (payment.getAmount() <= 0) {
            logger.warn("Validación fallida: amount inválido");
            return false;
        }
        if (payment.getCurrency() == null || payment.getCurrency().isBlank()) {
            logger.warn("Validación fallida: currency vacío");
            return false;
        }
        if (payment.getMethod() == null || payment.getMethod().isBlank()) {
            logger.warn("Validación fallida: method vacío");
            return false;
        }
        if (payment.getStatus() == null || payment.getStatus().isBlank()) {
            logger.warn("Validación fallida: status vacío");
            return false;
        }
        if (payment.getPaymentDate() == null) {
            logger.warn("Validación fallida: paymentDate nulo");
            return false;
        }
        if (payment.getDescription() == null || payment.getDescription().isBlank()) {
            logger.warn("Validación fallida: description vacía");
            return false;
        }
        return true;
    }
}