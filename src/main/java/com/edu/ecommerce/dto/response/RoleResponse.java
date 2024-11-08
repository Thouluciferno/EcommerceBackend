package com.edu.ecommerce.dto.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {

    String name;

    @JsonIgnore
    String description;

    @JsonIgnore
    Set<PermissionResponse> permissions;
}
