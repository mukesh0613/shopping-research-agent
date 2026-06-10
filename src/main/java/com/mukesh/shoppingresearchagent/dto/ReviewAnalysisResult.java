package com.mukesh.shoppingresearchagent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewAnalysisResult {

    private Double averageRating;

    private String sentiment;

    private String strengths;

    private String weaknesses;

    private String summary;
}