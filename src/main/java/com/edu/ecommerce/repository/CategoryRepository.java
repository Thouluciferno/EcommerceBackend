package com.edu.ecommerce.repository;

import com.edu.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);


    @Query("SELECT c FROM Category c WHERE c.name LIKE %:name%")
    List<Category> findByNameContaining(String name);


    @Query("SELECT c FROM Category c WHERE c.name IN :categoryFilters")
    List<Category> findByNameIn(List<String> categoryFilters);
}