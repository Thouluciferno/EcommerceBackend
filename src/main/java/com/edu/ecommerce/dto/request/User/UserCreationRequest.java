package com.edu.ecommerce.dto.request.User;

import com.edu.ecommerce.validator.PasswordConstraint;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 3, max = 20, message = "USERNAME_INVALID")
    String username;

    @PasswordConstraint(min = 3, message = "PASSWORD_INVALID")
    String password;

    @Email(message = "EMAIL_INVALID")
    String email;

    @Size(min = 10, max = 10,message = "NUMBERPHONE_INVALID")
    String numberPhone;

}
