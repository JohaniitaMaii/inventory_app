-- Categorías
CREATE TABLE IF NOT EXISTS categorias(
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

-- Estructura Organizacional
CREATE TABLE IF NOT EXISTS sectores (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS puestos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    sector_id INTEGER REFERENCES sectores(id),
    puede_vender BOOLEAN DEFAULT FALSE,
    puede_gestionar_inventario BOOLEAN DEFAULT FALSE,
    puede_gestionar_empleados BOOLEAN DEFAULT FALSE,
    puede_ver_reportes_sensibles BOOLEAN DEFAULT FALSE,
    puede_gestionar_clientes BOOLEAN DEFAULT FALSE,
    puede_realizar_mantenimiento BOOLEAN DEFAULT FALSE,
    puede_configurar_sistema BOOLEAN DEFAULT FALSE,
    activo BOOLEAN DEFAULT TRUE
);

-- Usuarios y Herencia (Table-per-Class o Joined)

CREATE TABLE IF NOT EXISTS empleados (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    cuit_dni VARCHAR(20) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    domicilio VARCHAR(255),
    estado BOOLEAN DEFAULT TRUE,
    rol VARCHAR(50),
    legajo VARCHAR(20) UNIQUE,
    puesto_id INTEGER REFERENCES puestos(id),
    disponibilidad VARCHAR(50), -- PRESENTE, AUSENTE
    salario_base DECIMAL(19, 2),
    fecha_ingreso DATE
);

CREATE TABLE IF NOT EXISTS clientes (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    cuit_dni VARCHAR(20) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    domicilio VARCHAR(255),
    estado BOOLEAN DEFAULT TRUE,
    rol VARCHAR(50),
    categoria_fiscal VARCHAR(50),
    tipo_cliente VARCHAR(50)
);

-- Productos
CREATE TABLE IF NOT EXISTS productos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    marca VARCHAR(100),
    codigo_barras VARCHAR(50) UNIQUE NOT NULL,
    precio DECIMAL(19, 2) NOT NULL,
    stock INTEGER NOT NULL,
    categoria_id INTEGER REFERENCES categorias(id),
    activo BOOLEAN DEFAULT TRUE
);