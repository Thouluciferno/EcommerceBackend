package com.edu.ecommerce.controller;


import com.edu.ecommerce.dto.request.Product.ProductCreationRequest;
import com.edu.ecommerce.dto.response.Product.ProductResponse;
import com.edu.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductController {

    ProductService productService;


    @RequestMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

//    find by id
    @RequestMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping("/create")
    public ProductResponse createProduct(@RequestBody @Valid ProductCreationRequest request) {
        return productService.createProduct(request);
    }

//    find with category filter
    @PostMapping("/filter")
    public List<ProductResponse> getFilteredProducts(@RequestBody List<String> categoryFilters) {
        return productService.getFilteredProducts(categoryFilters);

    }
}
