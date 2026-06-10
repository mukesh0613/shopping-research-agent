package com.mukesh.shoppingresearchagent.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mukesh.shoppingresearchagent.dto.ExternalProductDto;
import com.mukesh.shoppingresearchagent.dto.ProductApiResponse;
import com.mukesh.shoppingresearchagent.dto.ProductSearchResult;
import com.mukesh.shoppingresearchagent.mapper.ProductMapper;
import com.mukesh.shoppingresearchagent.service.ProductSearchApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseProductProvider {

    protected final ProductSearchApiService apiService;

    protected final ObjectMapper objectMapper;

    protected final ProductMapper productMapper;

    protected ProductSearchResult searchProduct(
            String query,
            String providerName
    ) throws Exception {

        try {

            String url =
                    "https://dummyjson.com/products/search?q="
                            + query;

            String response =
                    apiService.search(url);

            log.info(
                    "{} API Response: {}",
                    providerName,
                    response
            );

            ProductApiResponse apiResponse =
                    objectMapper.readValue(
                            response,
                            ProductApiResponse.class
                    );

            if (apiResponse.getProducts() != null
                    &&
                    !apiResponse.getProducts().isEmpty()) {

                ExternalProductDto product =
                        apiResponse
                                .getProducts()
                                .get(0);

                ProductSearchResult result =
                        productMapper.toSearchResult(
                                product,
                                providerName
                        );

                adjustPriceByProvider(
                        result,
                        providerName
                );

                return result;
            }

            log.warn(
                    "No products found from API for provider: {}. Using fallback.",
                    providerName
            );

            return createFallbackProduct(
                    query,
                    providerName
            );

        } catch (Exception ex) {

            log.error(
                    "Provider API failed. Using fallback for {}",
                    providerName,
                    ex
            );

            return createFallbackProduct(
                    query,
                    providerName
            );
        }
    }

    private ProductSearchResult createFallbackProduct(
            String query,
            String providerName
    ) {

        double price =
                switch (providerName.toUpperCase()) {

                    case "AMAZON" -> 399.0;

                    case "FLIPKART" -> 379.0;

                    case "BLINKIT" -> 410.0;

                    case "BIGBASKET" -> 395.0;

                    default -> 399.0;
                };

        double rating =
                switch (providerName.toUpperCase()) {

                    case "AMAZON" -> 4.6;

                    case "FLIPKART" -> 4.4;

                    case "BLINKIT" -> 4.7;

                    case "BIGBASKET" -> 4.3;

                    default -> 4.5;
                };

        return ProductSearchResult
                .builder()
                .store(providerName)
                .productName(query)
                .price(price)
                .rating(rating)
                .productUrl(
                        "https://"
                                + providerName.toLowerCase()
                                + ".com"
                )
                .build();
    }

    private void adjustPriceByProvider(
            ProductSearchResult result,
            String providerName
    ) {

        switch (providerName.toUpperCase()) {

            case "FLIPKART" ->
                    result.setPrice(
                            result.getPrice() - 20
                    );

            case "BLINKIT" ->
                    result.setPrice(
                            result.getPrice() + 15
                    );

            case "BIGBASKET" ->
                    result.setPrice(
                            result.getPrice() - 5
                    );

            default -> {
            }
        }
    }
}