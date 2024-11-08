package com.edu.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.ecommerce.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}
