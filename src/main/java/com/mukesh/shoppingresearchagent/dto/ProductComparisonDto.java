package com.mukesh.shoppingresearchagent.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductComparisonDto {
    private String productName;

    private String cheapestSource;

    private Double cheapestPrice;

    private List<StorePriceDto> storePrices;
}
