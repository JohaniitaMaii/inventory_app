package com.portfolio.inventory_app.model.enums;


public enum Rol {
    SUPER_ADMIN, // Acceso total, gestión de empleados y sectores
    ADMIN,       // Gestión operativa (inventario, ventas)
    SELLER,      // El "Vendedor" de tu lista (puede ver prospectos y ayudar clientes)
    CLIENT,      // El cliente que se loguea para comprar
    GUEST;       // Usuario no registrado (solo ve catálogo público)
}
