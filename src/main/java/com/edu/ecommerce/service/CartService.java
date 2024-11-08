package com.edu.ecommerce.service;


import com.edu.ecommerce.dto.request.Cart.CartCreationRequest;
import com.edu.ecommerce.dto.response.Cart.CartResponse;
import com.edu.ecommerce.entity.Cart;
import com.edu.ecommerce.entity.Product;
import com.edu.ecommerce.entity.User;
import com.edu.ecommerce.exception.AppException;
import com.edu.ecommerce.exception.ErrorCode;
import com.edu.ecommerce.mapper.CartMapper;
import com.edu.ecommerce.mapper.ProductMapper;
import com.edu.ecommerce.mapper.UserMapper;
import com.edu.ecommerce.repository.CartRepository;
import com.edu.ecommerce.repository.ProductRepository;
import com.edu.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CartService {

    CartRepository cartRepository;
    CartMapper cartMapper;

    ProductRepository productRepository;
    ProductMapper productMapper;

    UserRepository userRepository;
    UserMapper userMapper;


    public List<CartResponse> getAllCarts() {
        return cartMapper.toCartResponseList(cartRepository.findAll());
    }


    public CartResponse createCart(CartCreationRequest request) {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Cart existingCart = cartRepository.findByUserAndProduct(user, product);
        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + request.getQuantity());
            return cartMapper.toCartResponse(cartRepository.save(existingCart));
        }


        Cart cart = cartMapper.toCart(request);
        cart.setUser(user);
        cart.setProduct(product);
        return cartMapper.toCartResponse(cartRepository.save(cart));


    }

    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }


}
