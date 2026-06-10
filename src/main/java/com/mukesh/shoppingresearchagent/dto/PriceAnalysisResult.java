package com.mukesh.shoppingresearchagent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceAnalysisResult {

    private boolean productsFound;

    private String message;

    private String bestStore;

    private Double lowestPrice;

    private Double highestPrice;

    private Double averagePrice;

    private Double savings;

    private String bestRatedStore;

    private Double highestRating;

    private String bestValueStore;
}