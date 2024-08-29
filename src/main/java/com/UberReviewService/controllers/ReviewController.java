package com.UberReviewService.controllers;

import com.UberReviewService.adaptors.CreateReviewDtoToReviewAdaptor;
import com.UberReviewService.dtos.CreateReviewDto;
import com.UberReviewService.dtos.ReviewDto;
import com.UberReviewService.models.Review;
import com.UberReviewService.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private ReviewService reviewService;

    private CreateReviewDtoToReviewAdaptor createReviewDtoToReviewAdaptor;

    public ReviewController(ReviewService reviewService,CreateReviewDtoToReviewAdaptor createReviewDtoToReviewAdaptor){
        this.reviewService = reviewService;
        this.createReviewDtoToReviewAdaptor=createReviewDtoToReviewAdaptor;
    }

    @PostMapping
    public ResponseEntity<?> publishReview(@RequestBody CreateReviewDto request) {

        Review incomingReview=this.createReviewDtoToReviewAdaptor.convertDto(request);
       if(incomingReview == null) {
           return new ResponseEntity<>("Invalid Arguments",HttpStatus.BAD_REQUEST);
       }

        Review review = this.reviewService.publishReview(incomingReview);
        ReviewDto response=ReviewDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .booking(review.getBooking().getId())
                .created_at(review.getCreatedAt())
                .updated_at(review.getUpdatedAt())
                                     .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        List<Review> reviews = this.reviewService.findAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable Long reviewId) {
        try {
            Optional<Review> review = this.reviewService.findReviewById(reviewId);
            return new ResponseEntity<>(review, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId) {
        try {
            boolean isDeleted = this.reviewService.deleteReviewById(reviewId);
            if(!isDeleted) return new ResponseEntity<>("Unable to delete Review", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable Long reviewId, @RequestBody Review request){
        try {
            Review review = this.reviewService.updateReview(reviewId, request);
            return new ResponseEntity<>(review, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
