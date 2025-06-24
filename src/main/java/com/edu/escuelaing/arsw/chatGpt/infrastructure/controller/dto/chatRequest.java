package com.edu.escuelaing.arsw.chatGpt.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class chatRequest {
    String prompt;
}
