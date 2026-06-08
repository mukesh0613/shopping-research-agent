package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.dto.*;
import com.mukesh.shoppingresearchagent.entity.ProductPriceHistoryEntity;
import com.mukesh.shoppingresearchagent.exception.PriceHistoryNotFoundException;
import com.mukesh.shoppingresearchagent.repository.ProductPriceHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PriceAnalyticsService {
    private final PriceHistoryService priceHistoryService;
    private final ProductPriceHistoryRepository productPriceHistoryRepository;


    public ProductPriceHistoryEntity
    getLowestPrice(String productName) {

        return productPriceHistoryRepository
                .findFirstByProductNameOrderByPriceAsc(
                        productName
                )
                .orElseThrow(() ->
                        new PriceHistoryNotFoundException(
                                "No price history found"
                        )
                );
    }

    public ProductPriceHistoryEntity getLatestPrice(String productName){
        return productPriceHistoryRepository
                .findFirstByProductNameOrderByCapturedAtDesc(
                        productName
                )
                .orElseThrow(() ->
                        new PriceHistoryNotFoundException(
                                "No price history found"
                        )
                );
    }

    public Double getAveragePrice(
            String productName
    ) {

        return productPriceHistoryRepository.getAveragePrice(
                productName
        );
    }


    public PriceSummaryDto
    getPriceSummary(String productName) {

        ProductPriceHistoryEntity latest =
                getLatestPrice(productName);

        ProductPriceHistoryEntity lowest =
                getLowestPrice(productName);

        Double average =
                getAveragePrice(productName);

        return PriceSummaryDto.builder()
                .productName(productName)
                .latestPrice(latest.getPrice())
                .lowestPrice(lowest.getPrice())
                .averagePrice(average)
                .build();
    }

    public PriceTrendDto getPriceTrend(
            String productName
    ) {

        List<ProductPriceHistoryEntity> history =
                productPriceHistoryRepository
                        .findTop7ByProductNameOrderByCapturedAtDesc(
                                productName
                        );

        List<Double> prices =
                history.stream()
                        .map(ProductPriceHistoryEntity::getPrice)
                        .toList();

        String trend = "STABLE";

        if(prices.size() >= 2){

            Double latest = prices.get(0);
            Double oldest = prices.get(prices.size() - 1);

            if(latest > oldest){
                trend = "INCREASING";
            }
            else if(latest < oldest){
                trend = "DECREASING";
            }
        }

        return PriceTrendDto.builder()
                .productName(productName)
                .prices(prices)
                .trend(trend)
                .build();
    }

    public BuyRecommendationDto
    getBuyRecommendation(String productName) {

        double latest =
                getLatestPrice(productName)
                        .getPrice();

        double lowest =
                getLowestPrice(productName)
                        .getPrice();

        double average =
                getAveragePrice(productName);

        String recommendation;
        String reason;

        if (latest <= lowest + 10) {

            recommendation = "EXCELLENT_DEAL";

            reason =
                    "Current price is very close to the historical lowest price.";

        } else if (latest <= average) {

            recommendation = "GOOD_TIME_TO_BUY";

            reason =
                    "Current price is below the historical average.";

        } else {

            recommendation = "WAIT_FOR_PRICE_DROP";

            reason =
                    "Current price is above the historical average.";
        }

        return BuyRecommendationDto.builder()
                .productName(productName)
                .latestPrice(latest)
                .lowestPrice(lowest)
                .averagePrice(average)
                .recommendation(recommendation)
                .reason(reason)
                .build();
    }


    public ProductAnalyticsDto getAnalytics(
            String productName
    ) {

        double latest =
                getLatestPrice(productName)
                        .getPrice();

        double lowest =
                getLowestPrice(productName)
                        .getPrice();

        double average =
                getAveragePrice(productName);

        String recommendation;

        if (latest <= lowest + 10) {

            recommendation = "EXCELLENT_DEAL";

        } else if (latest <= average) {

            recommendation = "GOOD_TIME_TO_BUY";

        } else {

            recommendation = "WAIT_FOR_PRICE_DROP";
        }

        return ProductAnalyticsDto.builder()
                .productName(productName)
                .latestPrice(latest)
                .lowestPrice(lowest)
                .averagePrice(average)
                .recommendation(recommendation)
                .build();
    }



    public ProductComparisonDto getProductComparison(
            String productName
    ) {

        List<ProductPriceHistoryEntity> history =
                productPriceHistoryRepository
                        .findByProductName(productName);

        Map<String, ProductPriceHistoryEntity> latestPrices =
                new HashMap<>();

        for(ProductPriceHistoryEntity entry : history){

            ProductPriceHistoryEntity existing =
                    latestPrices.get(entry.getSource());

            if(existing == null ||
                    entry.getCapturedAt()
                            .isAfter(existing.getCapturedAt())){

                latestPrices.put(
                        entry.getSource(),
                        entry
                );
            }
        }

        List<StorePriceDto> storePrices =
                latestPrices.values()
                        .stream()
                        .map(price ->
                                StorePriceDto.builder()
                                        .source(price.getSource())
                                        .price(price.getPrice())
                                        .build()
                        )
                        .toList();

        StorePriceDto cheapest =
                storePrices.stream()
                        .min(
                                Comparator.comparing(
                                        StorePriceDto::getPrice
                                )
                        )
                        .orElseThrow();

        return ProductComparisonDto.builder()
                .productName(productName)
                .cheapestSource(
                        cheapest.getSource()
                )
                .cheapestPrice(
                        cheapest.getPrice()
                )
                .storePrices(storePrices)
                .build();
    }

}
