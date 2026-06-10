package com.mukesh.shoppingresearchagent.dto;

import lombok.Data;

@Data
public class ExternalProductDto {

    private String title;

    private Double price;

    private Double rating;

    private String thumbnail;
}