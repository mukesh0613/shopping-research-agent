package com.mukesh.shoppingresearchagent.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreProductDto {
    private String productName;

    private String source;

    private Double price;

    private Double rating;

}
