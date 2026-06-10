package com.mukesh.shoppingresearchagent.agent.review;

import com.mukesh.shoppingresearchagent.dto.ProductReviewSummary;
import com.mukesh.shoppingresearchagent.dto.ReviewAnalysisResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewAnalysisAgent {

    public ReviewAnalysisResult analyze(
            ProductReviewSummary reviewSummary
    ) {

        Double rating =
                reviewSummary.getAverageRating();

        String sentiment;

        if (rating >= 4.5) {

            sentiment = "Very Positive";

        } else if (rating >= 4.0) {

            sentiment = "Positive";

        } else if (rating >= 3.0) {

            sentiment = "Neutral";

        } else {

            sentiment = "Negative";
        }

        return ReviewAnalysisResult
                .builder()
                .averageRating(
                        rating
                )
                .sentiment(
                        sentiment
                )
                .strengths(
                        "Good customer feedback"
                )
                .weaknesses(
                        "No major weaknesses identified"
                )
                .summary(
                        reviewSummary.getReviewSummary()
                )
                .build();
    }
}