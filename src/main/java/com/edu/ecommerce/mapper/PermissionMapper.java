package com.edu.ecommerce.mapper;

import org.mapstruct.Mapper;

import com.edu.ecommerce.dto.request.PermissionRequest;
import com.edu.ecommerce.dto.response.PermissionResponse;
import com.edu.ecommerce.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
