package com.edu.escuelaing.arsw.chatGpt.infrastructure.controller.dto;

import lombok.Data;

@Data
public class Message {
    private String role;
    private String content;
}

