package com.portfolio.inventory_app;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
// CREADO PARA TESTEAR CONEXION A BASE DE DATOS
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/inventory_db"; // 'postgres' es la bd por defecto
        String user = "postgres";
        String password = "maiiroot";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("✅ ¡PC configurada y conectada a PostgreSQL exitosamente!");
            }
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }
}
