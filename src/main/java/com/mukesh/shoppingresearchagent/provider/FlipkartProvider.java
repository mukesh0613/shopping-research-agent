package com.mukesh.shoppingresearchagent.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mukesh.shoppingresearchagent.dto.ProductSearchRequest;
import com.mukesh.shoppingresearchagent.dto.ProductSearchResult;
import com.mukesh.shoppingresearchagent.mapper.ProductMapper;
import com.mukesh.shoppingresearchagent.service.ProductSearchApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FlipkartProvider
        extends BaseProductProvider
        implements EcommerceProvider {

    public FlipkartProvider(
            ProductSearchApiService apiService,
            ObjectMapper objectMapper,
            ProductMapper productMapper
    ) {

        super(
                apiService,
                objectMapper,
                productMapper
        );
    }

    @Override
    @Retryable(
            retryFor = Exception.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public ProductSearchResult search(
            ProductSearchRequest request
    ) {

        try {

            return searchProduct(
                    request.getProductName(),
                    "Flipkart"
            );

        } catch (Exception ex) {

            log.error(
                    "Flipkart search failed",
                    ex
            );

            throw new RuntimeException(
                    ex
            );
        }
    }

    @Override
    public String getProviderName() {

        return "FLIPKART";
    }
}