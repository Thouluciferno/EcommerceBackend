package com.edu.ecommerce.dto.response.User;

import java.util.Set;

import com.edu.ecommerce.dto.response.RoleResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    Long id;
    String username;

    @JsonIgnore
    String password;

    String email;

    String numberPhone;

    Set<RoleResponse> roles;
}
