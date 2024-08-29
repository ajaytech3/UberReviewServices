package com.UberReviewService.adaptors;

import com.UberReviewService.dtos.CreateReviewDto;
import com.UberReviewService.models.Review;

public interface CreateReviewDtoToReviewAdaptor {

    public Review convertDto(CreateReviewDto createReviewDto);
}
