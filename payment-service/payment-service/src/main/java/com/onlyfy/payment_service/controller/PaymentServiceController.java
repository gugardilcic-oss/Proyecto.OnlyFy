package com.onlyfy.payment_service.controller;

import com.onlyfy.payment_service.model.PaymentServiceModel;
import com.onlyfy.payment_service.service.PaymentServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentServiceController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceController.class);

    private final PaymentServiceService paymentService;

    public PaymentServiceController(PaymentServiceService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentServiceModel>> obtenerPagos() {
        return ResponseEntity.ok(paymentService.listarPagos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable int id) {
        return paymentService.obtenerPorId(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Pago no encontrado con id: {}", id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Pago no encontrado");
                });
    }

    @PostMapping
    public ResponseEntity<?> crearPago(@RequestBody PaymentServiceModel payment) {
        if (!paymentService.datosValidos(payment)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Datos del pago inválidos");
        }
        PaymentServiceModel nuevo = paymentService.crearPago(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPago(@PathVariable int id, @RequestBody PaymentServiceModel payment) {
        if (!paymentService.datosValidos(payment)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Datos del pago inválidos");
        }
        return paymentService.actualizarPago(id, payment)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Pago no encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPago(@PathVariable int id) {
        if (paymentService.eliminarPago(id)) {
            return ResponseEntity.ok("Pago eliminado correctamente");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pago no encontrado");
    }
}