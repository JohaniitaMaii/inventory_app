package com.portfolio.inventory_app.exception;

public class BusinessLogicException extends RuntimeException {
    public BusinessLogicException(String message) {
        super("Atención de Negocio: " + message);
    }
}
