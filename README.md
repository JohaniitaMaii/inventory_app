# Sistema de Gestión de Inventario - Backend Java

Este proyecto es un sistema robusto de backend diseñado para gestionar la lógica compleja de inventarios y ventas, priorizando la integridad de los datos y una arquitectura de servicios desacoplada.

## 🚀 Arquitectura y Lógica de Negocio

El sistema implementa una **jerarquía de servicios** estratégica para delegar responsabilidades y validaciones:
**Venta -> Empleado -> Puesto**

* **Desacoplamiento:** Aplicación estricta de *Single Responsibility Principle* (SRP).
* **Validaciones en Cascada:** La lógica de negocio garantiza que una venta solo se procese si el empleado está activo y posee los permisos asociados a su puesto.
* **Integridad Transaccional:** Uso de `@Transactional` para asegurar la consistencia en operaciones complejas de stock y facturación.

## 🛠 Stack Tecnológico

* **Lenguaje:** Java 17
* **Framework:** Spring Boot 3.x (Spring Data JPA, Spring Web)
* **Persistencia:** Hibernate / PostgreSQL
* **Herramientas de Desarrollo:** IntelliJ IDEA, dBeaver, Postman
* **Gestión de Dependencias:** Maven

## ⚙️ Funcionalidades Clave

* **API RESTful:** Endpoints estructurados para operaciones CRUD y reportes específicos.
* **Manejo Global de Excepciones:** Respuestas estandarizadas (JSON) ante errores de validación o de negocio.
* **Modelado de Datos:** Diseño relacional optimizado con foco en la normalización y performance de consultas.

## 🛠 Instalación y Ejecución

1. Clonar el repositorio.
2. Configurar las credenciales de PostgreSQL en `src/main/resources/application.properties`.
3. Ejecutar `mvn clean install` para buildear el proyecto y descargar dependencias.
4. Correr la aplicación (`InventoryApplication.java`).

---
*Desarrollado con foco en escalabilidad y mentalidad "Get things done".*
