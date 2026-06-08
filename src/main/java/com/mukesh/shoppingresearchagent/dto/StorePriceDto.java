package com.mukesh.shoppingresearchagent.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StorePriceDto {
    private String source;
    private Double price;
}
