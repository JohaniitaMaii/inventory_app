package com.portfolio.inventory_app.util;

import com.portfolio.inventory_app.exception.EmailInvalidException;

public class DataValidator {

    private static final String EMAIL_PARTNER = "^[A-Za-z0-9+_.-]+@(.+)$";

    public void validarEmail(String email) {
        if (email == null || !email.matches(EMAIL_PARTNER)) {
            throw new EmailInvalidException("Email invalido");
        }
    }
}
