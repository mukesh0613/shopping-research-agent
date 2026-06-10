package com.mukesh.shoppingresearchagent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchMetricsResult {

    private List<ProviderMetrics> providerMetrics;

    private int totalProviders;

    private int successfulProviders;

    private int failedProviders;

    private long averageResponseTimeMs;

    private String fastestProvider;

    private String slowestProvider;
}