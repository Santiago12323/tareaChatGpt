package com.edu.escuelaing.arsw.chatGpt.infrastructure.controller.dto;
import lombok.Data;

@Data
public class Usage {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
}
