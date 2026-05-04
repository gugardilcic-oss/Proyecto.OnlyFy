package com.onlyfy.notification_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class NotificationServiceRequestDTO {
    @NotBlank
    @NotNull

    @NotBlank(message = "User ID cannot be blank")
    private String userId;
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Message cannot be blank")
    private String message;
    @NotBlank(message = "Type cannot be blank")
    private String type; 

    


    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
}

public void setType(String type) {
        this.type = type;
}
}
