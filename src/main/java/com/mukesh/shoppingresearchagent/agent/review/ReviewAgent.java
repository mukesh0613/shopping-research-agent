package com.mukesh.shoppingresearchagent.agent.review;

import com.mukesh.shoppingresearchagent.dto.ProductReviewSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewAgent {

    public ProductReviewSummary getReviewSummary(
            String productName
    ) {

        return ProductReviewSummary.builder()
                .averageRating(4.5)
                .reviewSummary(
                        "Customers like the taste, quality and value for money."
                )
                .build();
    }
}