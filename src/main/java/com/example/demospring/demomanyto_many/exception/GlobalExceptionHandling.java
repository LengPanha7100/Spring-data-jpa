package com.example.demospring.demomanyto_many.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
@RestController
@RestControllerAdvice
public class GlobalExceptionHandling {
    @ResponseStatus(HttpStatus.BAD_REQUEST)

    @ExceptionHandler({CustomNotfoundException.class})
    public ProblemDetail handlerAllNotFoundException(CustomNotfoundException e) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setType(URI.create("about:blank"));
        problemDetail.setTitle("Not Found");
        problemDetail.setStatus(404);
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handlerArgumentNotValidException(MethodArgumentNotValidException e) {

        HashMap<String, String> errors = new HashMap<>();
        for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid Request");
        problemDetail.setType(URI.create("about:blank"));
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("errors", errors);
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleMethodValidationException(HandlerMethodValidationException e) {
        HashMap<String, String> errors = new HashMap<>();

        for (var parameterError : e.getAllValidationResults()) {
            String parameterName = parameterError.getMethodParameter().getParameterName();
            //get error message
            for (var error : parameterError.getResolvableErrors()) {
                errors.put(parameterName, error.getDefaultMessage());
            }
        }
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Invalid request"
        );
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        problemDetail.setProperty("error", errors);
        return problemDetail;
    }
}
