package com.edu.ecommerce.service;

import com.edu.ecommerce.controller.CategoryController;
import com.edu.ecommerce.dto.request.Category.CategoryCreationRequest;
import com.edu.ecommerce.dto.request.RoleRequest;
import com.edu.ecommerce.dto.response.Category.CategoryResponse;
import com.edu.ecommerce.dto.response.RoleResponse;
import com.edu.ecommerce.entity.Category;
import com.edu.ecommerce.exception.AppException;
import com.edu.ecommerce.exception.ErrorCode;
import com.edu.ecommerce.mapper.CategoryMapper;
import com.edu.ecommerce.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;


    public List<CategoryResponse> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }

    //    create
    public CategoryResponse createCategory(CategoryCreationRequest categoryRequest) {

        Category category = categoryRepository.findByName(categoryRequest.getName());

        if (category != null) {
            throw new AppException(ErrorCode.CATEGORY_EXISTED);
        } else {
            category = categoryMapper.toCategory(categoryRequest);
            categoryRepository.save(category);
            return categoryMapper.toCategoryResponse(category);
        }


    }


    public CategoryResponse getCategory(Long id) {

        Category category = categoryRepository
                .findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        return categoryMapper.toCategoryResponse(category);

    }

//    find by name

    public List<CategoryResponse> getCategoriesByName(String name) {

        return categoryRepository
                .findByNameContaining(name)
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();

                }

    public List<CategoryResponse> getFilteredCategories(List<String> categoryFilters) {

        return categoryRepository
                .findByNameIn(categoryFilters)
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();

    }
}
