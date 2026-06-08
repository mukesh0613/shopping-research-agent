package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.dto.ProductExtractionResult;
import org.springframework.stereotype.Service;

@Service
public class ProductExtractionService {
    public ProductExtractionResult extractProduct(String query){
        String q = query.toLowerCase();

        if(q.contains("peanut butter")) {

            return new ProductExtractionResult(
                    "Pintola Peanut Butter"
            );
        }

        if(q.contains("iphone")) {

            return new ProductExtractionResult(
                    "iPhone 15"
            );
        }

        return new ProductExtractionResult(
                "Unknown Product"
        );

    }
}
