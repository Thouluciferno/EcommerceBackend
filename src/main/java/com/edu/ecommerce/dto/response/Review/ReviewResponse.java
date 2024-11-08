package com.edu.ecommerce.dto.response.Review;


import com.edu.ecommerce.dto.response.Product.ProductResponse;
import com.edu.ecommerce.dto.response.User.UserResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewResponse {

    Long id;

    String comment;

    double rating;

    UserResponse user;

    ProductResponse product;
}
