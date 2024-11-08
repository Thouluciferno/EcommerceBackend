package com.edu.ecommerce.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {PasswordValidator.class})
public @interface PasswordConstraint {
    String message() default "password must be at least 8 characters";

    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
