package com.edu.ecommerce.service;


import com.edu.ecommerce.dto.request.OrderDetail.OrderDetailCreationRequest;
import com.edu.ecommerce.dto.response.OrderDetail.OrderDetailResponse;
import com.edu.ecommerce.entity.User;
import com.edu.ecommerce.exception.AppException;
import com.edu.ecommerce.exception.ErrorCode;
import com.edu.ecommerce.mapper.OrderDetailMapper;
import com.edu.ecommerce.repository.OrderDetailRepository;
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
public class OrderDetailService {

    OrderDetailRepository orderDetailRepository;
    OrderDetailMapper orderDetailMapper;

    UserRepository userRepository;



     void createOrderDetail(OrderDetailCreationRequest request) {
        orderDetailRepository.save(orderDetailMapper.toOrderDetail(request));
    }

     void deleteOrderDetail(Long id) {
        orderDetailRepository.deleteById(id);
    }


//     void updateOrderDetail(Long id, Long quantity) {
//        orderDetailRepository.updateQuantity(id, quantity);
//    }

     public List<OrderDetailResponse> getAllOrderDetails() {
        return orderDetailRepository
                .findAll()
                .stream()
                .map(orderDetailMapper::toOrderDetailResponse)
                .toList();
    }

}
