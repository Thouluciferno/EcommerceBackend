package com.edu.ecommerce.mapper;

import com.edu.ecommerce.dto.request.Product.ProductCreationRequest;
import com.edu.ecommerce.dto.response.Product.ProductResponse;
import com.edu.ecommerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface ProductMapper {

    // Map ProductCreationRequest to Product entity
//    dto
    Product toProduct(ProductCreationRequest request);

    // Map Product entity to ProductResponse DTO



//    entity
    ProductResponse toProductResponse(Product product);

    // Map list of Product entities to list of ProductResponse DTOs
    List<ProductResponse> toProductResponseList(List<Product> all);

}
