package com.mukesh.shoppingresearchagent.mapper;

import com.mukesh.shoppingresearchagent.dto.ExternalProductDto;
import com.mukesh.shoppingresearchagent.dto.ProductSearchResult;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductSearchResult toSearchResult(

            ExternalProductDto product,

            String providerName

    ) {

        return ProductSearchResult
                .builder()

                .store(
                        providerName
                )

                .productName(
                        product.getTitle()
                )

                .price(
                        product.getPrice()
                )

                .rating(
                        product.getRating()
                )

                .productUrl(
                        product.getThumbnail()
                )

                .build();
    }
}