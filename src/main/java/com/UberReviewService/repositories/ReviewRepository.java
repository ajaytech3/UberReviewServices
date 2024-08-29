package com.UberReviewService.repositories;

import com.UberReviewService.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    Integer countAllByRatingIsLessThanEqual(Double givenRating); //It will give all the count of review which contain rating which is 3 or less than 3 like 3,2,1

    List<Review> findAllByRatingIsLessThanEqual(Double givenRating);//It will give all the review which contain  rating which is 3 or less than 3 like 3,2,1

    List<Review> findAllByCreatedAtBefore(Date date);//It will give all the review which is created before the date which is passed

    @Query("select r from Booking b inner join Review r where b.id = :bookingId")
    Review findReviewByBookingId(Long bookingId);//it helps to fetch the Review by booking id

}
