package com.edu.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    // 200 - Success
    USER_SUCCESS(200, "User action succeeded", HttpStatus.OK),
    CATEGORY_SUCCESS(200, "Category action succeeded", HttpStatus.OK),
    PRODUCT_SUCCESS(200, "Product action succeeded", HttpStatus.OK),

    // 400 - Bad Request
    USERNAME_INVALID(400, "Username must be at least {min} characters long", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(400, "Password must be at least {min} characters long", HttpStatus.BAD_REQUEST),
    EMAIL_INVALID(400, "Email must be a valid email address", HttpStatus.BAD_REQUEST),
    NUMBERPHONE_INVALID(400, "Number phone must be at least {min} characters long", HttpStatus.BAD_REQUEST),

    // 400 - Bad Request
    INVALID_KEY(400, "Invalid access key", HttpStatus.BAD_REQUEST),
    PARSE_EXCEPTION(400, "Failed to parse the input", HttpStatus.BAD_REQUEST),
    CATEGORY_NAME_INVALID(400, "Category name must be at least {min} characters long", HttpStatus.BAD_REQUEST),
    CATEGORY_DESCRIPTION_INVALID(400, "Category description must be at least {min} characters long", HttpStatus.BAD_REQUEST),
    PRODUCT_NAME_INVALID(400, "Product name must be at least {min} characters long", HttpStatus.BAD_REQUEST),
    PRODUCT_DESCRIPTION_INVALID(400, "Product description must be at least {min} characters long", HttpStatus.BAD_REQUEST),
    PRODUCT_PRICE_INVALID(400, "Product price must be greater than 0", HttpStatus.BAD_REQUEST),
    PRODUCT_QUANTITY_INVALID(400, "Product quantity must be greater than 0", HttpStatus.BAD_REQUEST),

    // 401 - Unauthorized
    USER_NOT_LOGIN(401, "User is not logged in", HttpStatus.UNAUTHORIZED),
    UNAUTHENTICATED(401, "User is unauthenticated", HttpStatus.UNAUTHORIZED),

    // 403 - Forbidden
    USER_NOT_ACTIVE(403, "User account is not active", HttpStatus.FORBIDDEN),
    UNAUTHORIZED(403, "You don't have permission to access", HttpStatus.FORBIDDEN),
    CATEGORY_NOT_ALLOW(403, "You are not allowed to perform this action", HttpStatus.FORBIDDEN),

    // 404 - Not Found
    USER_NOT_FOUND(404, "User not found", HttpStatus.NOT_FOUND),
    CATEGORY_NOT_FOUND(404, "Category not found", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND(404, "Product not found", HttpStatus.NOT_FOUND),

    // 500 - Internal Server Error
    UNCATEGORIZED_EXCEPTION(500, "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(500, "User already exists", HttpStatus.INTERNAL_SERVER_ERROR),
    CATEGORY_EXISTED(500, "Category already exists", HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCT_EXISTED(500, "Product already exists", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_ID_NOT_PROVIDED(500, "User ID not provided", HttpStatus.INTERNAL_SERVER_ERROR),
    // REVIEW

    RATING_MIN_VALUE(500, "Rating must be at least {min}", HttpStatus.INTERNAL_SERVER_ERROR),
    RATING_MAX_VALUE(500, "Rating must be at most {max}", HttpStatus.INTERNAL_SERVER_ERROR),
    COMMENT_INVALID(500, "You need to provide a comment", HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCT_ID_NOT_PROVIDED(500, "Product ID not provided", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_PROVIDED(500, "You need to login", HttpStatus.INTERNAL_SERVER_ERROR),

    // 600 - Internal Server Error
    INTERNAL_SERVER_ERROR(600, "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    ORDER_NOT_FOUND(404, "Order not found", HttpStatus.NOT_FOUND),

    //    RESOURCE
    RESOURCE_NOT_FOUND(404, "Resource not found", HttpStatus.NOT_FOUND),
    ;


    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;
}
