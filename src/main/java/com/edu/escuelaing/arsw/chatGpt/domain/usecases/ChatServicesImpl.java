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
        return client.consultChatGpt(prompt)
                .map(response -> response.getChoices().get(0).getMessage().getContent());
    }


}
