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
    nombre VARCHAR(100) UNIQUE NOT NULL,
    sector_id BIGINT REFERENCES sectores(id),
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
    rol VARCHAR(50),
    nombre VARCHAR(100) NOT NULL,
    domicilio VARCHAR(255),
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    cuit_dni VARCHAR(20) UNIQUE NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    puesto_id BIGINT REFERENCES puestos(id),
    fecha_ingreso DATE,
    fecha_egreso DATE,
    modalidad VARCHAR(50), -- PRESENCIAL, REMOTO, HÍBRIDO
    jornada VARCHAR(50), -- COMPLETA, MEDIA_JORNADA, POR_HORAS
    contrato VARCHAR(50), -- TEMPORAL, PERMANENTE, POR_PROYECTO
    disponibilidad VARCHAR(50), -- PRESENTE, AUSENTE
    legajo VARCHAR(20) UNIQUE,
    salario_base DECIMAL(19, 2),
    comision DECIMAL(19, 2), -- Porcentaje de comisión para vendedores
    sucursal VARCHAR(100), -- Sucursal a la que está asignado el empleado
    objetivo_mensual DECIMAL(19, 2), -- Objetivo de ventas mensual para vendedores
    obra_social VARCHAR(100), -- Obra social del empleado
    cbu VARCHAR(22) UNIQUE -- CBU para pagos de nómina
);

CREATE TABLE IF NOT EXISTS clientes (
    id BIGSERIAL PRIMARY KEY,
    rol VARCHAR(50),
    nombre VARCHAR(100) NOT NULL,
    domicilio VARCHAR(255),
    email VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    cuit_dni VARCHAR(20) UNIQUE NOT NULL,
    estado BOOLEAN DEFAULT TRUE,
    tipo_cliente VARCHAR(50),
    comportamiento VARCHAR(50), -- FRECUENTE, OCASIONAL, NUEVO
    categoria_fiscal VARCHAR(50),
    origen VARCHAR(100), -- Origen del cliente (referencia, publicidad, etc.)
    puntuacion INTEGER,
    preferencia VARCHAR(100) -- Preferencia de productos o categorías
);

-- Productos
CREATE TABLE IF NOT EXISTS productos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    marca VARCHAR(100),
    codigo_barras VARCHAR(50) UNIQUE NOT NULL,
    precio DECIMAL(19, 2) NOT NULL,
    precio_costo DECIMAL(19, 2),
    margen_ganancia DECIMAL(5, 2), -- Porcentaje de margen de ganancia
    iva DECIMAL(5, 2), -- Porcentaje de IVA
    stock_actual INTEGER NOT NULL,
    stock_minimo INTEGER,
    categoria_id BIGINT REFERENCES categorias(id),
    activo BOOLEAN DEFAULT TRUE
);

-- Tabla de Venta
CREATE TABLE venta (
    id BIGSERIAL PRIMARY KEY,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(19, 2) NOT NULL,
    cliente_id BIGINT NOT NULL,
    empleado_id BIGINT NOT NULL,
    CONSTRAINT fk_venta_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    CONSTRAINT fk_venta_empleado FOREIGN KEY (empleado_id) REFERENCES empleados(id)
);

-- Tabla de DetalleVenta
CREATE TABLE detalle_venta (
    id BIGSERIAL PRIMARY KEY,
    venta_id BIGINT NOT NULL,
    producto_id BIGINT NOT NULL,
    cantidad INTEGER NOT NULL,
    precio_unitario DECIMAL(19, 2) NOT NULL,
    CONSTRAINT fk_detalle_venta FOREIGN KEY (venta_id) REFERENCES venta(id) ON DELETE CASCADE,
    CONSTRAINT fk_detalle_producto FOREIGN KEY (producto_id) REFERENCES productos(id)
);