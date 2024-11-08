package com.edu.ecommerce.service;


import com.edu.ecommerce.dto.request.Product.ProductCreationRequest;
import com.edu.ecommerce.dto.response.Product.ProductResponse;
import com.edu.ecommerce.entity.Category;
import com.edu.ecommerce.entity.Product;
import com.edu.ecommerce.exception.AppException;
import com.edu.ecommerce.exception.ErrorCode;
import com.edu.ecommerce.mapper.ProductMapper;
import com.edu.ecommerce.repository.CategoryRepository;
import com.edu.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;  // You'll need this to fetch the Category from the DB
    ProductMapper productMapper;

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findByIdWithCategory(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        // Use MapStruct to map Product to ProductResponse
        return productMapper.toProductResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        // Use MapStruct to map list of Product to list of ProductResponse
        return productMapper.toProductResponseList(productRepository.findAll());
    }
    public ProductResponse createProduct(ProductCreationRequest request) {
        // Fetch the Category by ID from the database
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

//       check name is unique
        if (productRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.PRODUCT_EXISTED);
        }

        // Map the ProductCreationRequest to Product entity
        Product product = productMapper.toProduct(request);

        // Set the Category in the Product entity
        product.setCategory(category);

        log.info("Product creation request: {}", request);

        // Save the product to the database
        product = productRepository.save(product);

        // Map the saved Product entity to ProductResponse DTO
        return productMapper.toProductResponse(product);
    }


    public ProductResponse getProduct(Long id) {
        return productMapper.toProductResponse(productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND)));
    }

    public List<ProductResponse> getFilteredProducts(List<String> categoryFilters) {

        if(categoryFilters == null || categoryFilters.isEmpty()) {
            return getAllProducts();
        }

        // Use MapStruct to map list of Product to list of ProductResponse
        log.info("categoryFilters: {}", categoryFilters);

        return productMapper.toProductResponseList(productRepository.findByCategoryNameIn(categoryFilters));


    }
}
