package com.UberReviewService.adaptors;

import com.UberReviewService.dtos.CreateReviewDto;
import com.UberReviewService.models.Booking;
import com.UberReviewService.models.Review;
import com.UberReviewService.repositories.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class CreateReviewDtoToReviewAdaptorImpl implements CreateReviewDtoToReviewAdaptor{

    private BookingRepository bookingRepository;

    public CreateReviewDtoToReviewAdaptorImpl(BookingRepository bookingRepository){
        this.bookingRepository=bookingRepository;
    }
    @Override
    public Review convertDto(CreateReviewDto createReviewDto) {
        Optional<Booking> booking=bookingRepository.findById(createReviewDto.getBookingId());

       return booking.map(value->Review.builder()
             .rating(createReviewDto.getRating())
             .content(createReviewDto.getContent())
             .booking(value)
             .build()).orElse(null);

    }
}
