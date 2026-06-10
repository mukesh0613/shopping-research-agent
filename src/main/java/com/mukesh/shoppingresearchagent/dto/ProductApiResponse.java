package com.mukesh.shoppingresearchagent.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductApiResponse {

    private List<ExternalProductDto> products;
}