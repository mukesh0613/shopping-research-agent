package com.mukesh.shoppingresearchagent.tools;

import com.mukesh.shoppingresearchagent.dto.ProductSearchResult;
import org.springframework.stereotype.Component;

@Component
public class AmazonTool implements SearchTool {

    @Override
    public String getToolName() {

        return "AMAZON";
    }

    @Override
    public ProductSearchResult search(
            String productName
    ) {

        return ProductSearchResult.builder()
                .store("Amazon")
                .productName(productName)
                .price(399.0)
                .rating(4.5)
                .productUrl("https://amazon.in")
                .build();
    }
}