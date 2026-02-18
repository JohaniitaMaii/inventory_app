package com.portfolio.inventory_app.exception;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String message) {
        super(message);
    }

    //public class NegativeStockException extends RuntimeException {
    //
    //    public NegativeStockException(String message) {
    //
    //        super("Validación de Inventario: " + message);
    //
    //    }
    //
    //}



}
