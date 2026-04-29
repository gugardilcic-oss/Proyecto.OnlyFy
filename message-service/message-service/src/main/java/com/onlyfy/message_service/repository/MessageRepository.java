package com.onlyfy.message_service.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.onlyfy.message_service.model.MessageModel;


public interface MessageRepository extends JpaRepository<MessageModel, Long> {
    



}

