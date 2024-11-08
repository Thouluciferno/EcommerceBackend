package com.edu.ecommerce.controller;

import com.edu.ecommerce.dto.request.Order.OrderCreationRequest;
import com.edu.ecommerce.dto.response.Order.OrderResponse;
import com.edu.ecommerce.entity.Order;
import com.edu.ecommerce.entity.OrderDetail;
import com.edu.ecommerce.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderController {

    OrderService orderService;

    @RequestMapping("/all")
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

//    find my Order Id by user_id

    @RequestMapping("/MyOrders")
    public List<OrderResponse> getMyOrder() {
        return orderService.getMyOrder();
    }

    @PostMapping("/create")
    public OrderResponse createOrder(@RequestBody @Valid OrderCreationRequest orderCreationRequest) {

        return orderService.createOrder(orderCreationRequest);
    }



}
