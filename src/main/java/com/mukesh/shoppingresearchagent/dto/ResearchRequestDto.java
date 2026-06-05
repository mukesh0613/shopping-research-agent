package com.mukesh.shoppingresearchagent.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

import javax.management.Query;

@Data
public class ResearchRequestDto {
    @NotBlank(message = "Query cannot be empty")
    private String query;
}
