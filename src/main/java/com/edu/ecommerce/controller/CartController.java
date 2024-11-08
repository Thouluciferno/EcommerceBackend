package com.edu.ecommerce.controller;

import com.edu.ecommerce.dto.request.Cart.CartCreationRequest;
import com.edu.ecommerce.dto.response.Cart.CartResponse;
import com.edu.ecommerce.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CartController {

    CartService cartService;

    @GetMapping
    public List<CartResponse> getAllCarts() {
        return cartService.getAllCarts();
    }

//    add cart
    @PostMapping()
     public CartResponse createCart( @RequestBody  @Valid CartCreationRequest request) {

//        just add cart , we need to check quantity and user in request

        return cartService.createCart(request);
    }





  }
