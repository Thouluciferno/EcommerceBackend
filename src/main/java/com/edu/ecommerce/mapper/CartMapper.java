package com.edu.ecommerce.mapper;

import com.edu.ecommerce.dto.request.Cart.CartCreationRequest;
import com.edu.ecommerce.dto.response.Cart.CartResponse;
import com.edu.ecommerce.entity.Cart;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")

public interface CartMapper {


    Cart toCart(CartCreationRequest request);

    CartResponse toCartResponse(Cart cart);

    List<CartResponse> toCartResponseList(List<Cart> all);

}
