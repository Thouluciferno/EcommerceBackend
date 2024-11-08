package com.edu.ecommerce.service;


import com.edu.ecommerce.dto.request.Order.OrderCreationRequest;
import com.edu.ecommerce.dto.request.OrderDetail.OrderDetailCreationRequest;
import com.edu.ecommerce.dto.response.Order.OrderResponse;
import com.edu.ecommerce.entity.Order;
import com.edu.ecommerce.entity.OrderDetail;
import com.edu.ecommerce.entity.User;
import com.edu.ecommerce.exception.AppException;
import com.edu.ecommerce.exception.ErrorCode;
import com.edu.ecommerce.mapper.OrderDetailMapper;
import com.edu.ecommerce.mapper.OrderMapper;
import com.edu.ecommerce.repository.InvalidatedTokenRepository;
import com.edu.ecommerce.repository.OrderDetailRepository;
import com.edu.ecommerce.repository.OrderRepository;
import com.edu.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
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
public class OrderService {

    OrderRepository orderRepository;
    OrderDetailRepository orderDetailRepository;
    UserRepository userRepository;

    OrderDetailMapper orderDetailMapper;
    OrderMapper orderMapper;


    public OrderResponse createOrder(OrderCreationRequest request) {

        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        // find user by id
        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        log.info("username: {}", name);
        log.info("user: {}", user);

        Order order = orderMapper.toEntity(request);


        order.setUser(user);

        Order savedOrder = orderRepository.save(order);

//         get all order details

        List<OrderDetail> orderDetails = request.getOrderDetails().stream()
                .map(orderDetailMapper::toOrderDetail)
                .toList();

        savedOrder.setOrderDetails(orderDetails);

        orderDetailRepository.saveAll(orderDetails);


        return orderMapper.toResponse(savedOrder);

    }
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll().stream().map(orderMapper::toResponse).toList();
    }


    public List<OrderResponse> getMyOrder() {

        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();


        // find user by id
        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));


        log.info("username: {}", name);
        log.info("user: {}", user);

//        find by UserId


        return orderRepository.findByUserId(user.getId()).stream().map(orderMapper::toResponse).toList();

    }
}
