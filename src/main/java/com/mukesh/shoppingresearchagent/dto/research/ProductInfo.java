package com.mukesh.shoppingresearchagent.dto.research;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {
    private String name;
    private Double price;
    private String description;

}
