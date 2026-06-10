package com.mukesh.shoppingresearchagent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingResearchResponse {

    private String productName;

    private PriceSearchResult priceSearchResult;

    private ProductReviewSummary reviewSummary;

    private PriceAnalysisResult priceAnalysis;

    private ReviewAnalysisResult reviewAnalysis;

    private String recommendation;
}