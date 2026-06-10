package com.mukesh.shoppingresearchagent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProviderMetrics {

    private String providerName;

    private long responseTimeMs;

    private boolean success;

    private boolean timeout;

    private String errorMessage;
}