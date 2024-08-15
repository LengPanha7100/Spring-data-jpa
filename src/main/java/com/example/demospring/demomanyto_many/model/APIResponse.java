package com.example.demospring.demomanyto_many.model;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class APIResponse <T>{
    private String message;
    private T payload;
    private HttpStatus status;
    private LocalDateTime dateTime;
}
