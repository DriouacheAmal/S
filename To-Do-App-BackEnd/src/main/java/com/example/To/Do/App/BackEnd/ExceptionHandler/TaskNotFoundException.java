package com.example.To.Do.App.BackEnd.ExceptionHandler;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
