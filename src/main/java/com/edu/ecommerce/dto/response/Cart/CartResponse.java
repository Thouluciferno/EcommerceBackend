package com.edu.ecommerce.dto.response.Cart;

import com.edu.ecommerce.dto.response.Product.ProductResponse;
import com.edu.ecommerce.dto.response.User.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {

     Long id;

     int quantity;

     UserResponse user;

     ProductResponse product;
}
