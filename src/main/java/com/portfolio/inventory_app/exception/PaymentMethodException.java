package com.portfolio.inventory_app.exception;

public class PaymentMethodException  extends RuntimeException {
    public PaymentMethodException(String message) {
        super("Error en Pasarela de Pago: " + message);
    }
}
