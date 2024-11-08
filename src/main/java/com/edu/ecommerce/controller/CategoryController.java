package com.edu.ecommerce.controller;


import com.edu.ecommerce.dto.request.ApiResponse;
import com.edu.ecommerce.dto.request.Category.CategoryCreationRequest;
import com.edu.ecommerce.dto.request.RoleRequest;
import com.edu.ecommerce.dto.response.Category.CategoryResponse;
import com.edu.ecommerce.dto.response.RoleResponse;
import com.edu.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryController {

      CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Find by ID
    @GetMapping("/{id}")
    public CategoryResponse getCategory(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    // Find by Name
    @GetMapping("/search")
    public List<CategoryResponse> getCategoriesByName(@RequestParam String name) {
        return categoryService.getCategoriesByName(name);
    }

    // Filter categories array from frontend
    @PostMapping("/filter")
    public List<CategoryResponse> getFilteredCategories(@RequestBody List<String> categoryFilters) {
        return categoryService.getFilteredCategories(categoryFilters);
    }

    @PostMapping
    public CategoryResponse createCategory(@RequestBody @Valid CategoryCreationRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }

}
