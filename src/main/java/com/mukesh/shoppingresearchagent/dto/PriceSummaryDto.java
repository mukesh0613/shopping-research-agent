package com.mukesh.shoppingresearchagent.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceSummaryDto {
    private String productName;

    private Double latestPrice;

    private Double lowestPrice;

    private Double averagePrice;
}
