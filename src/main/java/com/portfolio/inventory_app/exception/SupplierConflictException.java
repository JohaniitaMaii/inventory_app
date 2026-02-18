package com.portfolio.inventory_app.exception;

public class SupplierConflictException  extends RuntimeException {
    public SupplierConflictException(String message) {
        super("Conflicto con el Proveedor " + message);
    }
}
