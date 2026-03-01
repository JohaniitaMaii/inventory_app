package com.portfolio.inventory_app.security;

public record RegisterRequest(
        String name,
        String dni,
        String rol,
        String email,
        String password ) {}
