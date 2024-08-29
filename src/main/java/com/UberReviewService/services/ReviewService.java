package com.UberReviewService.services;

import com.UberReviewService.models.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ReviewService {

    public Optional<Review> findReviewById(long reviewId);

    public List<Review> findAllReviews();

    public boolean deleteReviewById(long reviewId);

    public Review updateReview(Long id, Review newReviewData);

    public Review publishReview(Review review);
}
