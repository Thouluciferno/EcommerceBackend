package com.edu.ecommerce.controller;


import com.edu.ecommerce.dto.request.Review.ReviewCreationRequest;
import com.edu.ecommerce.dto.response.Review.ReviewResponse;
import com.edu.ecommerce.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ReviewController {

    ReviewService reviewService;

    @RequestMapping
    public List<ReviewResponse> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @RequestMapping("/{id}")
    public ReviewResponse getReview(@PathVariable Long id) {
        return reviewService.getReview(id);
    }

//    create

    @PostMapping
    public ReviewResponse createReview(@RequestBody @Valid ReviewCreationRequest reviewResponse) {
        return reviewService.createReview(reviewResponse);
    }

}
