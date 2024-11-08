package com.edu.ecommerce.dto.request.Review;


import com.edu.ecommerce.entity.Product;
import com.edu.ecommerce.entity.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewCreationRequest {


    @Min(value = 1, message = "RATING_MIN_VALUE")
    @Max(value = 5, message = "RATING_MAX_VALUE")
    int rating;

    @Size(max = 500, message = "COMMENT_INVALID")
    String comment;

    @NotNull(message = "PRODUCT_ID_NOT_PROVIDED")
    Long productId;

    // User is no longer part of the request; it will be handled via authentication

}