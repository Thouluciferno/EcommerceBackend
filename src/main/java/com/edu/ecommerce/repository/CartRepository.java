package com.edu.ecommerce.repository;

import com.edu.ecommerce.entity.Cart;
import com.edu.ecommerce.entity.Product;
import com.edu.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.user = :user AND c.product = :product")
    Cart findByUserAndProduct(User user, Product product);
}