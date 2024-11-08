package com.edu.ecommerce.dto.response.Order;

import com.edu.ecommerce.dto.response.User.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {

    Long id;

    String orderDate;

    String paymentMethod;

    UserResponse user;
}
