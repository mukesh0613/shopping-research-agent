package com.mukesh.shoppingresearchagent.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ResearchResponseDto {
    private String query;
    private String report;
}
