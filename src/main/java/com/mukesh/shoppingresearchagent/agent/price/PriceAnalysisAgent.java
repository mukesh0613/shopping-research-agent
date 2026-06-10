package com.mukesh.shoppingresearchagent.agent.price;

import com.mukesh.shoppingresearchagent.dto.PriceAnalysisResult;
import com.mukesh.shoppingresearchagent.dto.PriceSearchResult;
import com.mukesh.shoppingresearchagent.dto.ProductSearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PriceAnalysisAgent {

    public PriceAnalysisResult analyze(
            PriceSearchResult priceSearchResult
    ) {

        List<ProductSearchResult> products =
                priceSearchResult.getProducts();

        if (products == null || products.isEmpty()) {

            return PriceAnalysisResult
                    .builder()
                    .productsFound(false)
                    .message(
                            "No products found matching the search criteria."
                    )
                    .build();
        }

        ProductSearchResult cheapestProduct =
                products.stream()
                        .min(
                                Comparator.comparing(
                                        ProductSearchResult::getPrice
                                )
                        )
                        .orElse(null);

        ProductSearchResult mostExpensiveProduct =
                products.stream()
                        .max(
                                Comparator.comparing(
                                        ProductSearchResult::getPrice
                                )
                        )
                        .orElse(null);

        ProductSearchResult bestRatedProduct =
                products.stream()
                        .max(
                                Comparator.comparing(
                                        ProductSearchResult::getRating
                                )
                        )
                        .orElse(null);

        ProductSearchResult bestValueProduct =
                products.stream()
                        .max(
                                Comparator.comparing(
                                        p ->
                                                p.getRating()
                                                        /
                                                        p.getPrice()
                                )
                        )
                        .orElse(null);

        double averagePrice =
                products.stream()
                        .mapToDouble(
                                ProductSearchResult::getPrice
                        )
                        .average()
                        .orElse(0);

        double savings =
                mostExpensiveProduct.getPrice()
                        -
                        cheapestProduct.getPrice();

        return PriceAnalysisResult
                .builder()

                .productsFound(true)

                .bestStore(
                        cheapestProduct.getStore()
                )

                .lowestPrice(
                        cheapestProduct.getPrice()
                )

                .highestPrice(
                        mostExpensiveProduct.getPrice()
                )

                .averagePrice(
                        averagePrice
                )

                .savings(
                        savings
                )

                .bestRatedStore(
                        bestRatedProduct.getStore()
                )

                .highestRating(
                        bestRatedProduct.getRating()
                )

                .bestValueStore(
                        bestValueProduct.getStore()
                )

                .build();
    }
}