package com.mukesh.shoppingresearchagent.agent.price;

import com.mukesh.shoppingresearchagent.agent.metrics.MetricsAgent;
import com.mukesh.shoppingresearchagent.dto.PriceSearchResult;
import com.mukesh.shoppingresearchagent.dto.ProductExtraction;
import com.mukesh.shoppingresearchagent.dto.ProductSearchRequest;
import com.mukesh.shoppingresearchagent.dto.ProductSearchResult;
import com.mukesh.shoppingresearchagent.dto.ProviderExecutionResult;
import com.mukesh.shoppingresearchagent.dto.ProviderMetrics;
import com.mukesh.shoppingresearchagent.dto.SearchMetricsResult;
import com.mukesh.shoppingresearchagent.provider.EcommerceProvider;
import com.mukesh.shoppingresearchagent.provider.EcommerceProviderRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceAgent {

    private final EcommerceProviderRegistry providerRegistry;

    private final ExecutorService providerExecutor;

    private final MetricsAgent metricsAgent;

    @Cacheable(
            value = "price-search",
            key = "#product.productName + '-' + #product.budget"
    )
    public PriceSearchResult findPrices(
            ProductExtraction product
    ) {

        ProductSearchRequest request =
                ProductSearchRequest.builder()
                        .productName(
                                product.getProductName()
                        )
                        .category(
                                product.getCategory()
                        )
                        .budget(
                                product.getBudget()
                        )
                        .build();

        List<CompletableFuture<ProviderExecutionResult>>
                futures =

                providerRegistry
                        .getProviders()
                        .stream()

                        .map(
                                provider ->

                                        CompletableFuture
                                                .supplyAsync(
                                                        () ->
                                                                executeProvider(
                                                                        provider,
                                                                        request
                                                                ),
                                                        providerExecutor
                                                )

                                                .orTimeout(
                                                        5,
                                                        TimeUnit.SECONDS
                                                )

                                                .exceptionally(
                                                        ex -> {

                                                            log.error(
                                                                    "Provider failed: {}",
                                                                    provider.getProviderName(),
                                                                    ex
                                                            );

                                                            return ProviderExecutionResult
                                                                    .builder()

                                                                    .metrics(
                                                                            ProviderMetrics
                                                                                    .builder()

                                                                                    .providerName(
                                                                                            provider.getProviderName()
                                                                                    )

                                                                                    .responseTimeMs(
                                                                                            5000
                                                                                    )

                                                                                    .success(
                                                                                            false
                                                                                    )

                                                                                    .timeout(
                                                                                            true
                                                                                    )

                                                                                    .errorMessage(
                                                                                            ex.getMessage()
                                                                                    )

                                                                                    .build()
                                                                    )

                                                                    .build();
                                                        }
                                                )
                        )

                        .toList();

        List<ProviderExecutionResult>
                executionResults =

                futures.stream()

                        .map(
                                CompletableFuture::join
                        )

                        .toList();

        List<ProductSearchResult>
                products =

                executionResults.stream()

                        .map(
                                ProviderExecutionResult::getProduct
                        )

                        .filter(
                                productResult ->
                                        productResult != null
                        )

                        .toList();

        if (product.getBudget() != null) {

            products =
                    products.stream()

                            .filter(
                                    item ->
                                            item.getPrice()
                                                    <=
                                                    product.getBudget()
                            )

                            .toList();
        }

        List<ProviderMetrics>
                metrics =

                executionResults.stream()

                        .map(
                                ProviderExecutionResult::getMetrics
                        )

                        .filter(
                                metric ->
                                        metric != null
                        )

                        .toList();

        SearchMetricsResult metricsResult =
                metricsAgent.analyze(
                        metrics
                );

        return PriceSearchResult
                .builder()

                .products(
                        products
                )

                .metrics(
                        metricsResult
                )

                .build();
    }

    private ProviderExecutionResult executeProvider(

            EcommerceProvider provider,

            ProductSearchRequest request

    ) {

        long startTime =
                System.currentTimeMillis();

        try {

            ProductSearchResult result =
                    provider.search(
                            request
                    );

            long endTime =
                    System.currentTimeMillis();

            ProviderMetrics metrics =

                    ProviderMetrics.builder()

                            .providerName(
                                    provider.getProviderName()
                            )

                            .responseTimeMs(
                                    Math.max(
                                            endTime - startTime,
                                            1
                                    )
                            )

                            .success(
                                    true
                            )

                            .timeout(
                                    false
                            )

                            .build();

            return ProviderExecutionResult
                    .builder()

                    .product(
                            result
                    )

                    .metrics(
                            metrics
                    )

                    .build();

        } catch (Exception ex) {

            long endTime =
                    System.currentTimeMillis();

            ProviderMetrics metrics =

                    ProviderMetrics.builder()

                            .providerName(
                                    provider.getProviderName()
                            )

                            .responseTimeMs(
                                    Math.max(
                                            endTime - startTime,
                                            1
                                    )
                            )

                            .success(
                                    false
                            )

                            .timeout(
                                    false
                            )

                            .errorMessage(
                                    ex.getMessage()
                            )

                            .build();

            return ProviderExecutionResult
                    .builder()

                    .metrics(
                            metrics
                    )

                    .build();
        }
    }
}