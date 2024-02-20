package com.example.To.Do.App.BackEnd.ExceptionHandler;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
