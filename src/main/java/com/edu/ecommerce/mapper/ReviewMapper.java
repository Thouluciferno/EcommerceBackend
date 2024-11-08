package com.edu.ecommerce.mapper;

import com.edu.ecommerce.dto.request.Review.ReviewCreationRequest;
import com.edu.ecommerce.dto.response.Review.ReviewResponse;
import com.edu.ecommerce.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ReviewMapper {

    Review toReview(ReviewCreationRequest request);

    ReviewResponse toReviewResponse(Review review);
}
