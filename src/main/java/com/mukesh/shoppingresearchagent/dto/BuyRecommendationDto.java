package com.mukesh.shoppingresearchagent.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
    public class BuyRecommendationDto {

    private String productName;

    private Double latestPrice;

    private Double lowestPrice;

    private Double averagePrice;

    private String recommendation;

    private String reason;
}

