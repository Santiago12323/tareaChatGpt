package com.edu.escuelaing.arsw.chatGpt.infrastructure.controller.dto;

import lombok.Data;

@Data
public class Choice {
    private int index;
    private Message message;
    private String finish_reason;
}
