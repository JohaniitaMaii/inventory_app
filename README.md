# Sistema de Gestión de Inventario - Backend Java

Este proyecto es un sistema robusto de backend diseñado para gestionar la lógica compleja de un inventario, priorizando la integridad de los datos y una arquitectura de servicios escalable.

## 🚀 Arquitectura y Lógica de Negocio

El corazón de este sistema es su **jerarquía de servicios**, diseñada para delegar responsabilidades y validaciones de forma eficiente:
**Venta -> Empleado -> Puesto**

- **Desacoplamiento:** Cada servicio tiene una responsabilidad única (Single Responsibility Principle).
- **Validaciones en Cascada:** Para registrar una venta, el sistema valida la existencia y estado del empleado, quien a su vez depende de las jerarquías y permisos de su puesto.
- **Integridad:** Asegura que ninguna transacción se realice sin cumplir las reglas de negocio del establecimiento.

## 🛠 Stack Tecnológico
- **Lenguaje:** Java 17+
- **Framework:** Spring Boot 3.x
- **Persistencia:** Spring Data JPA / Hibernate
- **Base de Datos:** PostgreSQL
- **Gestión de Dependencias:** Maven
- **Pruebas de API:** Postman

## ⚙️ Funcionalidades Clave
- CRUD completo de productos, ventas y empleados.
- Implementación de consultas personalizadas en JPA para reportes de inventario.
- Manejo global de excepciones para respuestas de API consistentes.
- Diseño de base de datos relacional optimizado con dBeaver.

## 🛠 Cómo ejecutar el proyecto
1. Clonar el repositorio.
2. Configurar la base de datos PostgreSQL en `src/main/resources/application.properties`.
3. Ejecutar `mvn clean install`.
4. Correr la aplicación desde el entorno (IntelliJ IDEA recomendado).
