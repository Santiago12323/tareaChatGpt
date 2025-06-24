package com.edu.escuelaing.arsw.chatGpt.infrastructure.controller;


import com.edu.escuelaing.arsw.chatGpt.domain.usecases.IaService;
import com.edu.escuelaing.arsw.chatGpt.infrastructure.controller.dto.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/chatgpt")
public class ChatController {

    @Autowired
    IaService service;

    @PostMapping("/generate")
    public Mono<ResponseEntity<ChatResponse>> generateResponse(@RequestBody String prompt) {
        System.out.println("OpenIA");

        return service.consult(prompt)
                .map(response -> ResponseEntity.ok(new ChatResponse(response)))
                .onErrorResume(e ->
                        Mono.just(ResponseEntity.internalServerError()
                                .body(new ChatResponse("Error: " + e.getMessage())))
                );
    }

}
