package com.portfolio.inventory_app.util;

import com.portfolio.inventory_app.model.*;
import com.portfolio.inventory_app.model.enums.Rol;
import com.portfolio.inventory_app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private SectorRepository sectorRepo;

    @Autowired
    private PuestoRepository puestoRepo;

    @Autowired
    private EmpleadoRepository empleadoRepo;

    @Override
    public void run(String... args) throws Exception {
        if (sectorRepo.count() == 0) {
            System.out.println("🌱 Iniciando carga de datos...");

            // 1. Crear Sector
            Sector ventas = new Sector();
            ventas.setNombre("Ventas");
            sectorRepo.save(ventas);

            // 2. Crear Puesto y asociarlo al Sector
            Puesto vendedor = new Puesto();
            vendedor.setNombre("Vendedor");
            vendedor.setSector(ventas); // Importante: usamos la relación que creamos ayer
            puestoRepo.save(vendedor);

            // 3. Crear Empleado Admin
            Empleado admin = new Empleado();
            admin.setNombre("Admin");
            admin.setEmail("admin@saas.com");
            admin.setCuitDni("20123456789"); // Como String, según lo charlado
            admin.setTelefono("123456789");   // Como String
            admin.setRol(Rol.SUPER_ADMIN);
            admin.setPuesto(vendedor);
            admin.setIngreso(new Date());
            admin.setEstado(true);
            admin.setLegajo("L-001");
            admin.setSalarioBase(100000.0);

            empleadoRepo.save(admin);

            System.out.println("✅ Datos de prueba cargados correctamente.");
        }
    }
}
