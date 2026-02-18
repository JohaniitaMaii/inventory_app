package com.portfolio.inventory_app.exception;

public class FiscalConfigException extends RuntimeException {
    public FiscalConfigException(String message) {
        super("Confifuración Fiscal Requerida: " + message);
    }
}
