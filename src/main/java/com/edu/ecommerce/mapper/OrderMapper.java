package com.edu.ecommerce.mapper;


import com.edu.ecommerce.dto.request.Order.OrderCreationRequest;
import com.edu.ecommerce.dto.response.Order.OrderResponse;
import com.edu.ecommerce.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderCreationRequest orderCreationRequest);

    OrderResponse toResponse(Order order);
}
