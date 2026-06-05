package com.mukesh.shoppingresearchagent.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequestDto {

    @NotBlank(message="Product name cannot be empty")
    private String name;

    @Positive(message = "Price must be greater than 0")
    private Double price;

   /* public ProductRequestDto() {
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }*/
}
