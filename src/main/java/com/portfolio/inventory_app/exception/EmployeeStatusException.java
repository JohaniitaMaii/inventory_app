package com.portfolio.inventory_app.exception;

public class EmployeeStatusException extends RuntimeException {
    public EmployeeStatusException(String message) {
        super("Estado del Empleado: " + message);
    }
}
