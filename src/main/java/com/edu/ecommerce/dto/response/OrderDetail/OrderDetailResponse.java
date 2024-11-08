package com.edu.ecommerce.dto.response.OrderDetail;

import com.edu.ecommerce.dto.response.Order.OrderResponse;
import com.edu.ecommerce.dto.response.Product.ProductResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {

    Long id;

    double price;

    int quantity;

    OrderResponse order;

    ProductResponse product;

}
