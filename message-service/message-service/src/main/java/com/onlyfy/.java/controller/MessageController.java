package com.onlyfy.message_service.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlyfy.message_service.model.MessageModel;

@RestController

public class MessageController {


    @GetMapping("/message")
public MessageModel getMessage() {
    return new MessageModel("Juan", "Hola, ¿cómo estás?", "2024-06-01", "10:30", "2024-06-01 10:00", "Hola");
}



}
