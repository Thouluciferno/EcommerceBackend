package com.edu.ecommerce.controller;


import com.edu.ecommerce.dto.response.OrderDetail.OrderDetailResponse;
import com.edu.ecommerce.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderDetailController {

    OrderDetailService orderDetailService;


    @GetMapping
    List<OrderDetailResponse> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }

//    get by user


}
