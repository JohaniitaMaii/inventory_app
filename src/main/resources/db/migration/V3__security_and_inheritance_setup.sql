-- 1. Crear la tabla maestra de USUARIOS
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    dni VARCHAR(20) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    domicilio VARCHAR(255),
    telefono VARCHAR(50),
    rol VARCHAR(20) NOT NULL,
    estado BOOLEAN DEFAULT TRUE
);

-- 2. MIGRACIÓN DE IDENTIDADES A USUARIOS
-- Migrar Empleados (Password temporal: '123456')
INSERT INTO usuarios (nombre, dni, email, password, domicilio, telefono, rol)
SELECT nombre, dni, email, '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uSyLnS', domicilio, telefono, rol
FROM empleados;

-- Migrar Clientes (Asignamos ROL 'CLIENT' y Password temporal)
INSERT INTO usuarios (nombre, dni, email, password, domicilio, telefono, rol)
SELECT nombre, dni, email, '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uSyLnS', domicilio, telefono, 'CLIENT'
FROM clientes;

-- 3. RECONSTRUCCIÓN DE TABLA EMPLEADOS
CREATE TABLE empleados_new (
    id BIGINT PRIMARY KEY REFERENCES usuarios(id) ON DELETE CASCADE,
    legajo VARCHAR(20) UNIQUE,
    disponibilidad VARCHAR(50),
    sucursal VARCHAR(100),
    objetivo_mensual DECIMAL(19, 2),
    obra_social VARCHAR(100),
    puesto_id BIGINT REFERENCES puestos(id),
    informacion_laboral_id BIGINT NOT NULL UNIQUE REFERENCES informacion_laboral(id)
);

INSERT INTO empleados_new (id, legajo, disponibilidad, sucursal, objetivo_mensual, obra_social, puesto_id, informacion_laboral_id)
SELECT u.id, e.legajo, e.disponibilidad, e.sucursal, e.objetivo_mensual, e.obra_social, e.puesto_id, e.informacion_laboral_id
FROM usuarios u JOIN empleados e ON u.dni = e.dni WHERE u.rol != 'CLIENT';

-- 4. RECONSTRUCCIÓN DE TABLA CLIENTES
CREATE TABLE clientes_new (
    id BIGINT PRIMARY KEY REFERENCES usuarios(id) ON DELETE CASCADE,
    tipo_cliente VARCHAR(50),
    comportamiento VARCHAR(50),
    categoria_fiscal VARCHAR(50),
    origen VARCHAR(100),
    puntuacion INTEGER,
    preferencia VARCHAR(100)
);

INSERT INTO clientes_new (id,  tipo_cliente, comportamiento, categoria_fiscal, origen, puntuacion, preferencia)
SELECT u.id, c.tipo_cliente, c.comportamiento, c.categoria_fiscal, c.origen, c.puntuacion, c.preferencia
FROM usuarios u JOIN clientes c ON u.dni = c.dni WHERE u.rol = 'CLIENT';

-- 5. LIMPIEZA Y RENOMBRE
DROP TABLE empleados CASCADE;
DROP TABLE clientes CASCADE;
ALTER TABLE empleados_new RENAME TO empleados;
ALTER TABLE clientes_new RENAME TO clientes;