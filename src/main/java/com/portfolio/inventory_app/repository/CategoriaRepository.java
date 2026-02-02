package com.portfolio.inventory_app.repository;

import com.portfolio.inventory_app.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Category, Long> {
    List<Category> findByActivoTrue();
}
