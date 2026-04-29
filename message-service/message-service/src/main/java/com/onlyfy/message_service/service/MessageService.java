package com.onlyfy.message_service.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onlyfy.message_service.model.MessageModel;
import com.onlyfy.message_service.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository repository;
    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public MessageModel save(MessageModel mensaje) {
        if (mensaje.getFecha() == null) {
            mensaje.setFecha(java.time.LocalDate.now().toString());
        }
        if (mensaje.getHora() == null) {
            mensaje.setHora(java.time.LocalTime.now().toString());
        }
        return repository.save(mensaje);

    }

    }

