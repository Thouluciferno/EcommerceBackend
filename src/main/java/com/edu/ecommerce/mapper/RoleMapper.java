package com.edu.ecommerce.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.edu.ecommerce.dto.request.RoleRequest;
import com.edu.ecommerce.dto.response.RoleResponse;
import com.edu.ecommerce.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
