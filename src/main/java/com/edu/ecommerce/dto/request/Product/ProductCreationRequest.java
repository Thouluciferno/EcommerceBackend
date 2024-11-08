package com.edu.ecommerce.dto.request.Product;

import com.edu.ecommerce.entity.Cart;
import com.edu.ecommerce.entity.Category;
import com.edu.ecommerce.entity.OrderDetail;
import com.edu.ecommerce.entity.Review;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCreationRequest {


    @NotNull(message = "Name cannot be null")
    @Size(min = 6, max = 100, message = "PRODUCT_NAME_INVALID")
    String name;

    @Size(min = 6,max = 500, message = "PRODUCT_DESCRIPTION_INVALID")
    String description;

    @NotNull(message = "Price cannot be null")
    @Min(value = 0, message = "PRODUCT_PRICE_INVALID")
    double price;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "PRODUCT_QUANTITY_INVALID")
    int quantity;

    @NotNull(message = "CATEGORY_ID_NOT_PROVIDED")
    Long categoryId;


}