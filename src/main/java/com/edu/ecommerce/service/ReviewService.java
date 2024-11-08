package com.edu.ecommerce.service;


import com.edu.ecommerce.dto.request.Review.ReviewCreationRequest;
import com.edu.ecommerce.dto.response.Review.ReviewResponse;
import com.edu.ecommerce.entity.Product;
import com.edu.ecommerce.entity.Review;
import com.edu.ecommerce.entity.User;
import com.edu.ecommerce.exception.AppException;
import com.edu.ecommerce.exception.ErrorCode;
import com.edu.ecommerce.mapper.ProductMapper;
import com.edu.ecommerce.mapper.ReviewMapper;
import com.edu.ecommerce.mapper.UserMapper;
import com.edu.ecommerce.repository.ProductRepository;
import com.edu.ecommerce.repository.ReviewRepository;
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
public class ReviewService {

    ReviewRepository reviewRepository;
    ProductRepository productRepository;
    UserRepository userRepository;


    ReviewMapper reviewMapper;
    UserMapper userMapper;

      public List<ReviewResponse> getAllReviews() {
        return reviewRepository.findAll().stream().map(reviewMapper::toReviewResponse).toList();
    }

    public ReviewResponse getReview(Long id) {
        return reviewMapper.toReviewResponse(reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found")));
    }

//     create

    public ReviewResponse createReview(ReviewCreationRequest reviewCreationRequest) {

        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        log.info("username: {}", name);
        log.info("user: {}", user);
//          find product
        Product product = productRepository.findById(reviewCreationRequest.getProductId()).orElseThrow(() ->
                new AppException(ErrorCode.PRODUCT_NOT_FOUND));


        Review review = reviewMapper.toReview(reviewCreationRequest);

        reviewRepository.save(review);

        review.setProduct(product);

        review.setUser(user);

        return reviewMapper.toReviewResponse(review);


    }


}
