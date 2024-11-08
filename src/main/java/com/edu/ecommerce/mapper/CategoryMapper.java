package com.edu.ecommerce.mapper;


import com.edu.ecommerce.dto.request.Category.CategoryCreationRequest;
import com.edu.ecommerce.dto.response.Category.CategoryResponse;
import com.edu.ecommerce.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryCreationRequest request);

    CategoryResponse toCategoryResponse(Category category);
}
