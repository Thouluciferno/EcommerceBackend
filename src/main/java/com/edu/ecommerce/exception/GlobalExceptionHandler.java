package com.edu.ecommerce.exception;

import java.text.ParseException;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.edu.ecommerce.dto.request.ApiResponse;

import jakarta.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final String MIN_ATTRIBUTE = "min";

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException e) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.status(ErrorCode.UNCATEGORIZED_EXCEPTION.getHttpStatusCode())
                .body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException e) {
        String enumKey = e.getFieldError().getDefaultMessage();

        ErrorCode err = ErrorCode.INVALID_KEY;
        Map<String, Object> attributes = null;

        try {
            err = ErrorCode.valueOf(enumKey);

            var constraintViolation = e.getBindingResult().getAllErrors().get(0).unwrap(ConstraintViolation.class);

            log.info("constraintViolation: {}", constraintViolation);

            attributes = constraintViolation.getConstraintDescriptor().getAttributes();

            log.info("attributes: {}", attributes.toString());

        } catch (IllegalArgumentException ex) {
            log.error("Exception: {}", ex);
        }

        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(err.getCode());
        apiResponse.setMessage(
                Objects.nonNull(attributes) ? mapAttribute(err.getMessage(), attributes) : err.getMessage());

        return ResponseEntity.status(err.getHttpStatusCode()).body(apiResponse);
    }

    //    map attribute

    private String mapAttribute(String message, Map<String, Object> attributes) {

        String minValue = attributes.get(MIN_ATTRIBUTE).toString();

        log.info("minValue: {}", minValue);

        return message.replace("{" + MIN_ATTRIBUTE + "}", minValue);
    }

    //    Parse exception

    @ExceptionHandler(value = ParseException.class)
    ResponseEntity<ApiResponse> handlingParseException(ParseException e) {

        ErrorCode err = ErrorCode.PARSE_EXCEPTION;

        return ResponseEntity.status(err.getHttpStatusCode())
                .body(ApiResponse.builder()
                        .code(err.getCode())
                        .message(err.getMessage())
                        .build());
    }

    //    403  forbidden

    @ExceptionHandler(value = AuthorizationDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AuthorizationDeniedException e) {
        ErrorCode err = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(err.getHttpStatusCode())
                .body(ApiResponse.builder()
                        .code(err.getCode())
                        .message(err.getMessage())
                        .build());
    }

//    NoResourceFoundException

    @ExceptionHandler(value = NoResourceFoundException.class)
    ResponseEntity<ApiResponse> handlingNoResourceFoundException(NoResourceFoundException e) {
        ErrorCode err = ErrorCode.RESOURCE_NOT_FOUND;

        return ResponseEntity.status(err.getHttpStatusCode())
                .body(ApiResponse.builder()
                        .code(err.getCode())
                        .message(err.getMessage())
                        .build());
    }
}
