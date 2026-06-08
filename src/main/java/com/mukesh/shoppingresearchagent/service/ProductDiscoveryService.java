package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.dto.ComparisonResultDto;
import com.mukesh.shoppingresearchagent.dto.StoreProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDiscoveryService {
    private final MockAmazonService amazonService;

    private final MockBlinkitService blinkitService;

    private final MockZeptoService zeptoService;

    private final ProductSnapshotService productSnapshotService;

    public List<StoreProductDto> discoverproducts(String query){
        List<StoreProductDto> products=new ArrayList<>();
        products.addAll(amazonService.searchProducts(query));
        products.addAll(blinkitService.searchProducts(query));
        products.addAll(zeptoService.searchProducts(query));
        productSnapshotService.saveSnapshots(products);
        return products;
    }


    public ComparisonResultDto compareProducts(
            String query
    ) {

        List<StoreProductDto> products =
                discoverproducts(query);

        StoreProductDto cheapest =
                products.stream()
                        .min(
                                Comparator.comparing(
                                        StoreProductDto::getPrice
                                )
                        )
                        .orElseThrow();

        return ComparisonResultDto.builder()
                .productName(
                        cheapest.getProductName()
                )
                .bestStore(
                        cheapest.getSource()
                )
                .bestPrice(
                        cheapest.getPrice()
                )
                .bestRating(
                        cheapest.getRating()
                )
                .build();
    }
}
