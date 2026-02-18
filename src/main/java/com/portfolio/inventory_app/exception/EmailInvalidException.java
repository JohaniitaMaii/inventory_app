package com.portfolio.inventory_app.exception;

public class EmailInvalidException extends RuntimeException {
    public EmailInvalidException(String message) {
        super("Email no válido: " + message);
    }
}
