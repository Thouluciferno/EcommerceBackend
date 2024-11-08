package com.edu.ecommerce.mapper;


import com.edu.ecommerce.dto.request.OrderDetail.OrderDetailCreationRequest;
import com.edu.ecommerce.dto.response.OrderDetail.OrderDetailResponse;
import com.edu.ecommerce.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

     @Mapping(target = "order", ignore = true) // Ignore back-reference to avoid recursion
     OrderDetail toOrderDetail(OrderDetailCreationRequest request);

     OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);

}
