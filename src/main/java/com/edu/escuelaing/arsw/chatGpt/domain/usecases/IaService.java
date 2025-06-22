package com.edu.escuelaing.arsw.chatGpt.domain.usecases;

import com.edu.escuelaing.arsw.chatGpt.infrastructure.controller.dto.ChatGptResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import reactor.core.publisher.Mono;

public interface IaService {
    Mono<String> consult(String prompt) throws JsonProcessingException;
}
