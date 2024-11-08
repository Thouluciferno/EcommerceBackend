package com.edu.ecommerce.dto.request.User;

import java.util.List;

import com.edu.ecommerce.validator.PasswordConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

//    @Size(min = 3, max = 20, message = "USERNAME_INVALID")
//    String username;
//
    @Email
    String email;

    @Size(min = 10, max = 10)
    String numberPhone;

    List<String> roles;
}
