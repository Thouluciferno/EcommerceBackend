package com.edu.ecommerce.dto.request.OrderDetail;

import com.edu.ecommerce.entity.Order;
import com.edu.ecommerce.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailCreationRequest {

    // Quantity should be a positive integer, e.g., at least 1
    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be greater than 0")
    int quantity;

    // Order reference, mandatory
    @NotNull(message = "Order cannot be null")
    Order order;

    // Product reference, mandatory
    @NotNull(message = "Product cannot be null")
    Product product;
}
