package com.mukesh.shoppingresearchagent.agent.metrics;

import com.mukesh.shoppingresearchagent.dto.ProviderMetrics;
import com.mukesh.shoppingresearchagent.dto.SearchMetricsResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricsAgent {

    public SearchMetricsResult analyze(
            List<ProviderMetrics> metrics
    ) {

        int successCount =

                (int) metrics.stream()
                        .filter(
                                ProviderMetrics::isSuccess
                        )
                        .count();

        int failedCount =
                metrics.size()
                        -
                        successCount;

        long averageResponseTime =

                (long) metrics.stream()

                        .filter(
                                ProviderMetrics::isSuccess
                        )

                        .mapToLong(
                                ProviderMetrics::getResponseTimeMs
                        )

                        .average()

                        .orElse(0);

        String fastestProvider =

                metrics.stream()

                        .filter(
                                ProviderMetrics::isSuccess
                        )

                        .min(
                                Comparator.comparingLong(
                                        ProviderMetrics::getResponseTimeMs
                                )
                        )

                        .map(
                                ProviderMetrics::getProviderName
                        )

                        .orElse("N/A");

        String slowestProvider =

                metrics.stream()

                        .filter(
                                ProviderMetrics::isSuccess
                        )

                        .max(
                                Comparator.comparingLong(
                                        ProviderMetrics::getResponseTimeMs
                                )
                        )

                        .map(
                                ProviderMetrics::getProviderName
                        )

                        .orElse("N/A");

        return SearchMetricsResult
                .builder()

                .providerMetrics(
                        metrics
                )

                .totalProviders(
                        metrics.size()
                )

                .successfulProviders(
                        successCount
                )

                .failedProviders(
                        failedCount
                )

                .averageResponseTimeMs(
                        averageResponseTime
                )

                .fastestProvider(
                        fastestProvider
                )

                .slowestProvider(
                        slowestProvider
                )

                .build();
    }
}