package com.edu.ecommerce.dto.request.Cart;

import com.edu.ecommerce.entity.Product;
import com.edu.ecommerce.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartCreationRequest {

//    @NotNull(message = "User cannot be null")
//    User user;

    @NotNull(message = "PRODUCT_NOT_FOUND")
    Long ProductId ;

    @Min(value = 1, message = "PRODUCT_QUANTITY_INVALID")
    int quantity;


}
