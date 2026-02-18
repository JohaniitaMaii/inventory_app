package com.portfolio.inventory_app.exception;

public class UnauthorizedRoleException extends RuntimeException {
    public UnauthorizedRoleException(String message) {
        super("Seguridad Nivel Puesto: " + message);
    }
}
