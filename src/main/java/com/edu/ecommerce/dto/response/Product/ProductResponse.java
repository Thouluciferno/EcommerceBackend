package com.edu.ecommerce.dto.response.Product;

import com.edu.ecommerce.dto.response.Category.CategoryResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {

    Long id;

    String name;

    String description;

    double price;

    int quantity;

    String image;

    CategoryResponse category;
}
