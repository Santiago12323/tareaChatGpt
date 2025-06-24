package com.edu.escuelaing.arsw.chatGpt.domain.usecases;

import com.edu.escuelaing.arsw.chatGpt.Exceptions.PromptValidationResult;
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
    public Mono<String> consult(String prompt) {
        System.out.println(prompt);
        PromptValidationResult validation = validatePrompt(prompt);

        if (!validation.isValid()) {
            return Mono.error(new IllegalArgumentException(validation.getErrorMessage()));
        }

        return client.consultChatGpt(prompt)
                .map(response -> response.getChoices().get(0).getMessage().getContent());
    }

    private PromptValidationResult validatePrompt(String prompt) {
        if (prompt == null || prompt.trim().isEmpty()) {
            return PromptValidationResult.error("El prompt está vacío.");
        }

        String cleanedPrompt = prompt.trim().toLowerCase();

        List<String> trivialInputs = List.of("hola", "hi", "hello", "buenos días", "hey", "¿?", "?", "...");

        if (trivialInputs.contains(cleanedPrompt)) {
            return PromptValidationResult.error("El prompt es demasiado trivial. Por favor, intenta con una pregunta más específica.");
        }

        if (cleanedPrompt.length() < 10) {
            return PromptValidationResult.error("El prompt es demasiado corto. Debe tener al menos 10 caracteres.");
        }

        String alphaOnly = cleanedPrompt.replaceAll("[^a-zA-Záéíóúñü\\s]", "");
        if (alphaOnly.trim().isEmpty()) {
            return PromptValidationResult.error("El prompt no contiene texto significativo.");
        }

        String[] words = alphaOnly.trim().split("\\s+");
        if (words.length < 3) {
            return PromptValidationResult.error("El prompt debe contener al menos tres palabras significativas.");
        }

        if (cleanedPrompt.matches("[0-9\\p{Punct}\\s]+")) {
            return PromptValidationResult.error("El prompt no puede ser solo números o signos.");
        }

        return PromptValidationResult.ok();
    }


}
