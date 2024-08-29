package com.UberReviewService.repositories;

import com.UberReviewService.models.Booking;
import com.UberReviewService.models.Driver;
import com.UberReviewService.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {


}
