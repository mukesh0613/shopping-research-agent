package com.mukesh.shoppingresearchagent.agent.cordinator;

import com.mukesh.shoppingresearchagent.agent.extraction.ProductExtractionAgent;
import com.mukesh.shoppingresearchagent.agent.price.PriceAgent;
import com.mukesh.shoppingresearchagent.agent.price.PriceAnalysisAgent;
import com.mukesh.shoppingresearchagent.agent.recommendation.RecommendationAgent;
import com.mukesh.shoppingresearchagent.agent.review.ReviewAgent;
import com.mukesh.shoppingresearchagent.agent.review.ReviewAnalysisAgent;
import com.mukesh.shoppingresearchagent.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCoordinatorAgent {

    private final PriceAgent priceAgent;

    private final PriceAnalysisAgent priceAnalysisAgent;

    private final ReviewAgent reviewAgent;

    private final ReviewAnalysisAgent reviewAnalysisAgent;

    private final RecommendationAgent recommendationAgent;

    private final ProductExtractionAgent productExtractionAgent;

    public ShoppingResearchResponse researchProduct(
            String query
    ) {

        ProductExtraction extractionResult =
                productExtractionAgent.extract(
                        query
                );

        PriceSearchResult priceSearchResult =
                priceAgent.findPrices(
                        extractionResult
                );

        PriceAnalysisResult priceAnalysis =
                priceAnalysisAgent.analyze(
                        priceSearchResult
                );

        ProductReviewSummary reviewSummary =
                reviewAgent.getReviewSummary(
                        extractionResult.getProductName()
                );

        ReviewAnalysisResult reviewAnalysis =
                reviewAnalysisAgent.analyze(
                        reviewSummary
                );

        String recommendation =
                recommendationAgent.generateRecommendation(
                        priceSearchResult.getProducts(),
                        priceAnalysis,
                        reviewAnalysis
                );

        return ShoppingResearchResponse
                .builder()
                .productName(
                        extractionResult.getProductName()
                )
                .priceSearchResult(
                        priceSearchResult
                )
                .reviewSummary(
                        reviewSummary
                )
                .priceAnalysis(
                        priceAnalysis
                )
                .reviewAnalysis(
                        reviewAnalysis
                )
                .recommendation(
                        recommendation
                )
                .build();
    }
}