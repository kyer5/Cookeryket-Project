package com.example.cookeryket_sb.exception;

public class CookeryketException extends RuntimeException {

    private final String message;

    public CookeryketException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

