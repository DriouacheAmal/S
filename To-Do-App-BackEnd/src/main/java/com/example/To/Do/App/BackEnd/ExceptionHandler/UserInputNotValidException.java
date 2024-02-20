package com.example.To.Do.App.BackEnd.ExceptionHandler;

public class UserInputNotValidException extends RuntimeException {
    public UserInputNotValidException(String message) {
        super(message);
    }
}

