package com.UberReviewService.services;

import com.UberReviewService.models.Review;
import com.UberReviewService.repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.FetchNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

//    public ReviewServiceImpl(ReviewRepository reviewRepository){
//        this.reviewRepository=reviewRepository;
//    }
    @Override
    public Optional<Review> findReviewById(long reviewId) throws EntityNotFoundException {
        Optional<Review> review;
        try {
            review = this.reviewRepository.findById(reviewId);
            if (review.isEmpty()) {
                throw new EntityNotFoundException("Review with id " + reviewId + " not found");
            }
        }catch (Exception e){
            e.printStackTrace();
            if(e.getClass() == EntityNotFoundException.class){
                throw new FetchNotFoundException("Review with id " + reviewId + " not found", reviewId);
            }
            throw new FetchNotFoundException("Unable to fetch, PLease try again later!", reviewId);
        }
        return review;
    }

    @Override
    public List<Review> findAllReviews() {
        return this.reviewRepository.findAll();
    }

    @Override
    public boolean deleteReviewById(long reviewId) {
        try {
            Review review = this.reviewRepository.findById(reviewId).orElseThrow(EntityNotFoundException::new);
            this.reviewRepository.delete(review);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Review updateReview(Long id, Review newReviewData) {
        Review review = this.reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(newReviewData.getRating() != null){
            review.setRating(newReviewData.getRating());
        }
        if(newReviewData.getContent() != null){
            review.setContent(newReviewData.getContent());
        }
        return this.reviewRepository.save(review);
    }

    @Override
    public Review publishReview(Review review) {
        return this.reviewRepository.save(review);
    }
}
