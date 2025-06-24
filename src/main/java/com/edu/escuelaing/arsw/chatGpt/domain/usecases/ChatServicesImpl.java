package com.edu.escuelaing.arsw.chatGpt.domain.usecases;

import com.edu.escuelaing.arsw.chatGpt.infrastructure.controller.dto.ChatGptResponse;
import com.edu.escuelaing.arsw.chatGpt.infrastructure.restclient.ChatGptWebClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ChatServicesImpl implements IaService {
    @Autowired
    ChatGptWebClient client;

    @Override
    public Mono<String> consult(String prompt){
        if (!isValidPrompt(prompt)) {
            return Mono.error(new IllegalArgumentException("La consulta no tiene suficiente valor para enviarla a ChatGPT."));
        }

        return client.consultChatGpt(prompt)
                .map(response -> response.getChoices().get(0).getMessage().getContent());
    }

    private boolean isValidPrompt(String prompt) {
        if (prompt == null || prompt.trim().isEmpty()) return false;

        String lowerPrompt = prompt.trim().toLowerCase();

        List<String> trivialInputs = List.of("hola", "hi", "hello", "buenos días", "hey");

        if (trivialInputs.contains(lowerPrompt)) return false;

        if (prompt.trim().length() < 10) return false;

        if (!prompt.matches(".*[a-zA-ZáéíóúÁÉÍÓÚñÑ].*")) return false;

        return true;
    }
}
