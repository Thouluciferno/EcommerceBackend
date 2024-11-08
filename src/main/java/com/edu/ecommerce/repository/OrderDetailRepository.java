package com.edu.ecommerce.repository;

import com.edu.ecommerce.entity.OrderDetail;
import com.edu.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}