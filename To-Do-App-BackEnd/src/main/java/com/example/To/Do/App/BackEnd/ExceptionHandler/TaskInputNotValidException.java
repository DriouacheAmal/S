package com.example.To.Do.App.BackEnd.ExceptionHandler;

public class TaskInputNotValidException extends RuntimeException {
    public TaskInputNotValidException(String message) {
        super(message);
    }
}
