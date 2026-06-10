package com.mukesh.shoppingresearchagent.tools;

import com.mukesh.shoppingresearchagent.dto.ProductSearchResult;
import org.springframework.stereotype.Component;

@Component
public class FlipkartTool implements SearchTool {

    @Override
    public String getToolName() {

        return "FLIPKART";
    }

    @Override
    public ProductSearchResult search(
            String productName
    ) {

        return ProductSearchResult.builder()
                .store("Flipkart")
                .productName(productName)
                .price(379.0)
                .rating(4.4)
                .productUrl("https://flipkart.com")
                .build();
    }
}