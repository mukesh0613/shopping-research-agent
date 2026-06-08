package com.mukesh.shoppingresearchagent.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComparisonResultDto {
    private String productName;

    private String bestStore;

    private Double bestPrice;

    private Double bestRating;
}
