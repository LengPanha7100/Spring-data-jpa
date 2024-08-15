package com.example.demospring.demomanyto_many.exception;

public class CustomNotfoundException extends RuntimeException{
    public CustomNotfoundException(String message) {
        super(message);
    }
}
