package com.cashrich.BackendAssignment.exception;

public class InvalidUserDetailsException extends RuntimeException {
    public InvalidUserDetailsException(String message) {
        super(message);
    }
}
