-- 1. Insertar Categorías de Productos
INSERT INTO categorias (nombre, activo) VALUES
('Gama Premium', true),
('Gama Alta', true),
('Gama Media', true),
('Gama Baja', true)
ON CONFLICT (nombre) DO NOTHING;

-- 2. Insertar Sectores
INSERT INTO sectores (nombre) VALUES
('Estratégico/Dirección'),
('IT/Seguridad'),
('Comercial/Ventas'),
('Producto/Logística'),
('Mantenimiento/Infraestructura')
ON CONFLICT (nombre) DO NOTHING;

-- 3. Insertar Puestos con sus Permisos (Delegación de validaciones)
-- Director General
INSERT INTO puestos (nombre, sector_id, puede_vender, puede_gestionar_inventario,
 puede_ver_reportes_sensibles, puede_gestionar_empleados, puede_gestionar_clientes,
 puede_realizar_mantenimiento, puede_configurar_sistema)
SELECT 'Director General', id, true, true, true, true, true, true, true FROM sectores WHERE nombre = 'Estratégico/Dirección'
ON CONFLICT DO NOTHING;

-- Vendedor
INSERT INTO puestos (nombre, sector_id, puede_vender)
SELECT 'Vendedor', id, true FROM sectores WHERE nombre = 'Comercial/Ventas'
ON CONFLICT DO NOTHING;

-- Jefe de Depósito
INSERT INTO puestos (nombre, sector_id, puede_vender, puede_gestionar_inventario)
SELECT 'Jefe de Depósito', id, false, true FROM sectores WHERE nombre = 'Producto/Logística'
ON CONFLICT DO NOTHING;

-- 4. Insertar Empleado Admin (Directo a la tabla empleados por herencia TABLE_PER_CLASS)
INSERT INTO empleados (nombre, email, cuit_dni, rol, estado, legajo, salario_base, fecha_ingreso, puesto_id)
SELECT 'Admin', 'admin@saas.com', '20123456789', 'SUPER_ADMIN', true, 'L-001', 100000.0, CURRENT_DATE, id
FROM puestos WHERE nombre = 'Director General'
ON CONFLICT (email) DO NOTHING;

INSERT INTO empleados(nombre, email, cuit_dni, rol, estado, legajo, salario_base, fecha_ingreso, puesto_id)
SELECT 'Vendedor', 'vendedor@miempresa.com', '20345679871', 'SELLER', true, 'V-001', 650000.0, current_date,
id FROM puestos WHERE nombre = 'Vendedor'  ON CONFLICT (email) DO NOTHING;

-- 5. Insertar Cliente Consumidor Final
INSERT INTO clientes (nombre, email, cuit_dni, estado, categoria_fiscal, tipo_cliente)
VALUES ('Cliente Consumidor Final', 'venta.mostrador@consumidor.final', '20-11222333-4', true, 'CONSUMIDOR_FINAL', 'VENTA_MOSTRADOR')
ON CONFLICT (email) DO NOTHING;

-- 6. Insertar Productos USANDO SUBCONSULTA PARA OBTENER EL ID DE LA CATEGORÍA
DO $$
DECLARE
    p_base TEXT;
    productos_base TEXT[] := ARRAY['Mouse', 'Teclado', 'Disco Duro', 'Monitor', 'Procesador', 'RAM', 'Tarjeta Gráfica', 'Fuente', 'Gabinete'];
BEGIN
    FOREACH p_base IN ARRAY productos_base LOOP
        FOR i IN 1..3 LOOP
            -- GAMA PREMIUM
            INSERT INTO productos (nombre, descripcion, precio, stock, codigo_barras, categoria_id, marca, activo)
            SELECT p_base, 'Modelo Pro Max v' || i, 150000.00, 12, 'SKU-PRM-' || UPPER(p_base) || i, id, 'Marca Premium', true
            FROM categorias WHERE nombre = 'Gama Premium'
            ON CONFLICT (codigo_barras) DO NOTHING;

            -- GAMA BAJA
            INSERT INTO productos (nombre, descripcion, precio, stock, codigo_barras, categoria_id, marca, activo)
            SELECT p_base, 'Modelo Basic Edition', 25000.00, 24, 'SKU-BAS-' || UPPER(p_base) || i, id, 'Marca Económica', true
            FROM categorias WHERE nombre = 'Gama Baja'
            ON CONFLICT (codigo_barras) DO NOTHING;
        END LOOP;
    END LOOP;
END $$;
