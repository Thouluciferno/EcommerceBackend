package com.edu.ecommerce.repository;

import com.edu.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.id = :id")
    Optional<Product> findByIdWithCategory(@Param("id") Long id);

    boolean existsByName(String name);

    @Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.category.name IN :categoryFilters")
    List<Product> findByCategoryNameIn(List<String> categoryFilters);
}