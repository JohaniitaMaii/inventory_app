package com.portfolio.inventory_app.util;

import com.portfolio.inventory_app.model.*;
import com.portfolio.inventory_app.model.enums.CategoriaFiscal;
import com.portfolio.inventory_app.model.enums.Disponibilidad;
import com.portfolio.inventory_app.model.enums.Rol;
import com.portfolio.inventory_app.model.enums.TipoCliente;
import com.portfolio.inventory_app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private CategoriaRepository categoriaRepo;
    @Autowired private ProductoRepository productoRepo;
    @Autowired private SectorRepository sectorRepo;
    @Autowired private PuestoRepository puestoRepo;
    @Autowired private EmpleadoRepository empleadoRepo;
    @Autowired private ClienteRepository clienteRepo;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("🌱 Iniciando carga de datos...");

        // 1. Categorías de Productos
        Map<String, CategoriaProductos> cats = new HashMap<>();
        String[] nombresCat = {"Gama Premium", "Gama Alta", "Gama Media", "Gama Baja"};
        for (String n : nombresCat) {
            cats.put(n, categoriaRepo.save(new CategoriaProductos(n, true)));
        }

        // 2. Productos (2 por categoría)
        // Ejemplo simplificado: Teclado Premium y Teclado Económico, etc.
        String[] productosBase = {"Mouse", "Teclado", "Disco Duro", "Monitor", "Procesador", "RAM", "Tarjeta Gráfica", "Fuente", "Gabinete"};
        for (String p : productosBase) {
            for (int i = 1; i <= 3; i++) {
                // GAMA PREMIUM
                productoRepo.save(new Producto(
                        p,                          // Nombre
                        "Modelo Pro Max v" + i,     // Descripcion (Separado del nombre)
                        true,                       // Activo
                        BigDecimal.valueOf(150000), // Precio (Ajustado a BigDecimal)
                        12,                         // Stock
                        "SKU-PRM-" + p + i,         // Código (Generado dinámicamente)
                        cats.get("Gama Premium"),   // Categoría
                        "Marca Premium"             // Marca
                ));

                // GAMA BAJA
                productoRepo.save(new Producto(
                        p,
                        "Modelo Basic Edition",
                        true,
                        BigDecimal.valueOf(25000),
                        24,
                        "SKU-BAS-" + p + i,
                        cats.get("Gama Baja"),
                        "Marca Económica"
                ));
            }
        }

        // 3. Sectores
        Map<String, Sector> sectores = new HashMap<>();
        String[] nombresSec =
                {"Estratégico/Dirección",
                "IT/Seguridad",
                "Comercial/Ventas",
                "Producto/Logística",
                "Mantenimiento/Infraestructura"};
        for (String nombre : nombresSec) {
            Sector s = sectorRepo.save(new Sector(nombre));
            sectores.put(nombre, s);
        }

        // 4. Puestos (Delegación de validaciones según tu jerarquía)
        Map<String, Puesto> puestosMap = new HashMap<>();

        puestosMap.put("Director General", puestoRepo.save(new Puesto(
                "Director General", sectores.get("Estratégico/Dirección"),
                true, true, true,
                true, true, true,
                true)));

        puestosMap.put("Vendedor", puestoRepo.save(new Puesto(
                "Vendedor", sectores.get("Comercial/Ventas"),
                true, false, false,
                false, true, false,
                false)));

        puestosMap.put("Jefe de Depósito", puestoRepo.save(new Puesto(
                "Jefe de Depósito", sectores.get("Producto/Logística"),
                false, true, false,
                false, false, false,
                false)));

        puestosMap.put("Técnico de Mantenimiento", puestoRepo.save(new Puesto(
                "Técnico de Mantenimiento", sectores.get("Mantenimiento/Infraestructura"),
                false, false, false,
                false, false, true,
                false)));

        puestosMap.put("Director Sistemas", puestoRepo.save(new Puesto(
                "Director Sistemas", sectores.get("IT/Seguridad"),
                false, true, false,
                false, false, true,
                true)));

        // 5. Crear Empleado Admin
        Empleado admin = new Empleado();
        admin.setNombre("Admin");
        admin.setEmail("admin@saas.com");
        admin.setCuitDni("20123456789");
        admin.setTelefono("123456789");
        admin.setRol(Rol.SUPER_ADMIN);
        admin.setPuesto(puestosMap.get("Director Ejecutivo"));
        admin.setIngreso(new Date());
        admin.setEstado(true);
        admin.setLegajo("L-001");
        admin.setSalarioBase(100000.0);
        empleadoRepo.save(admin);

        // 6. Crear Vendedores
        for (int i = 1; i <= 3; i++) {
            Empleado v = new Empleado();
            v.setNombre("Vendedor " + i);
            v.setEmail("vendedor" + i + "@tienda.com");
            v.setCuitDni("20-0000000-" + i);
            v.setRol(Rol.SELLER);
            v.setPuesto(puestosMap.get("Vendedor"));
            v.setDomicilio("Av Siempre Viva 87" + i);
            v.setIngreso(new Date());
            v.setEstado(true);
            v.setDisponibilidad(Disponibilidad.PRESENTE);
            v.setTelefono("1231231" + i);
            v.setLegajo("V-00" + i);
            v.setSalarioBase(50000.0);
            empleadoRepo.save(v);
        }

        // 7. Clientes (mínimo 5)
        Cliente consFinal = new Cliente();
        consFinal.setNombre("Cliente Consumidor Final");
        consFinal.setCuitDni("20-11222333-4");
        consFinal.setEmail("venta.mostrador@consdsumidor.final");
        consFinal.setCategoriaFiscal(CategoriaFiscal.CONSUMIDOR_FINAL);
        consFinal.setTipoCliente(TipoCliente.VENTA_MOSTRADOR);
        clienteRepo.save(consFinal);
        System.out.println("✔ Consumidor Final creado exitosamente.");

        for (int i = 1; i <= 5; i++) {
            Cliente c = new Cliente();
            c.setNombre("Cliente " + i);
            c.setEmail("cliente" + i + "@socialmedia.com");
            c.setCuitDni("20-2300000-" + i);
            c.setRol(Rol.CLIENT);
            c.setTelefono("1234432" + i);
            clienteRepo.save(c);
        }


        System.out.println("✅ Datos de prueba cargados correctamente.");

    }
}
