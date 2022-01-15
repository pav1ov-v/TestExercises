package com.example.testexercise.exception;

public class UserNotExistException extends Exception {
    public UserNotExistException(String message) {
        super(message);
    }
}
