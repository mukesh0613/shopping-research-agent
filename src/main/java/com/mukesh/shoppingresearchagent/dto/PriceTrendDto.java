package com.mukesh.shoppingresearchagent.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PriceTrendDto {
    private String productName;
    private List<Double> prices;
    private String trend;
}
