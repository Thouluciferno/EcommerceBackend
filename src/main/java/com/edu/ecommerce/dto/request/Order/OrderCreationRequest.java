package com.edu.ecommerce.dto.request.Order;


import com.edu.ecommerce.dto.request.OrderDetail.OrderDetailCreationRequest;
import com.edu.ecommerce.dto.response.OrderDetail.OrderDetailResponse;
import com.edu.ecommerce.entity.OrderDetail;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreationRequest {

    // Order date must be in the past or present, as future orders are not allowed
    @NotNull(message = "Order date cannot be null")
    Date orderDate = new Date();

    // Payment method is mandatory, and it must have at least 3 characters
    @NotNull(message = "Payment method cannot be null")
    @Size(min = 3, message = "Payment method must have at least 3 characters")
    String paymentMethod;

    Date createdAt = new Date();

    Date updatedAt = null;
//
//    // Deleted date can be null or must be a valid string representation of date
    String deletedAt = null;

    // Order details must not be null, and the list must contain at least 1 item
    @NotNull(message = "Order details cannot be null")
    @Size(min = 1, message = "At least one order detail must be provided")
    List<OrderDetailCreationRequest> orderDetails;

//    // User ID cannot be null
//    @NotNull(message = "User ID cannot be null")
//    Long userId;
}