package com.portfolio.inventory_app.security;

public record LoginRequest(
        String email,
        String password
) {}
