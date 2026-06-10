package com.mukesh.shoppingresearchagent.tools;

import com.mukesh.shoppingresearchagent.dto.ProductSearchResult;
import org.springframework.stereotype.Component;

@Component
public class BlinkitTool implements SearchTool {

    @Override
    public String getToolName() {

        return "BLINKIT";
    }

    @Override
    public ProductSearchResult search(
            String productName
    ) {

        return ProductSearchResult.builder()
                .store("Blinkit")
                .productName(productName)
                .price(410.0)
                .rating(4.6)
                .productUrl("https://blinkit.com")
                .build();
    }
}