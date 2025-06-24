package com.edu.escuelaing.arsw.chatGpt.infrastructure.restclient;


import com.edu.escuelaing.arsw.chatGpt.infrastructure.controller.dto.ChatGptResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ChatGptWebClient {

    @Autowired
    private WebClient webClient;
    @Autowired
    private ObjectMapper objectMapper;

    public Mono<ChatGptResponse> consultChatGpt(String prompt) {
        try {
            String json = buildRequestPayload(prompt);
            return webClient.post()
                    .uri("/chat/completions")
                    .bodyValue(json)
                    .retrieve()
                    .bodyToMono(ChatGptResponse.class);
        } catch (JsonProcessingException e) {
            return Mono.error(new RuntimeException("Error al construir el JSON de la solicitud", e));
        }
    }

    private String buildRequestPayload(String input) throws JsonProcessingException {

        Map<String, Object> payload = Map.of(
                "model", "gpt-4o",
                "messages", List.of(
                        Map.of("role", "user", "content", input)
                ),
                "max_tokens", 1000,
                "temperature", 0.7
        );

        return objectMapper.writeValueAsString(payload);
    }

}
