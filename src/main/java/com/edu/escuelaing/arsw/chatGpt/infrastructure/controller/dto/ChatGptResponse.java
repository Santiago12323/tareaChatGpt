package com.edu.escuelaing.arsw.chatGpt.infrastructure.controller.dto;

import lombok.Data;
import java.util.List;

@Data
public class ChatGptResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
}

