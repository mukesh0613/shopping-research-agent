package com.mukesh.shoppingresearchagent.controller;

import com.mukesh.shoppingresearchagent.dto.*;
import com.mukesh.shoppingresearchagent.entity.ProductPriceHistoryEntity;
import com.mukesh.shoppingresearchagent.service.PriceHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.mukesh.shoppingresearchagent.service.PriceAnalyticsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prices")
public class PriceHistoryController {
    private final PriceHistoryService priceHistoryService;
    private final PriceAnalyticsService priceAnalyticsService;

    @PostMapping("/test")
    public String saveTestPrice() {

        priceHistoryService.savePriceSnapshot(
                "Pintola Peanut Butter",
                "Amazon",
                355.0
        );

        return "Price saved successfully";
    }

    @GetMapping("/{productName}")
    public List<ProductPriceHistoryEntity>
    getPriceHistory(
            @PathVariable String productName
    ) {

        return priceHistoryService
                .getPriceHistory(productName);
    }

    @GetMapping("/{productName}/lowest")
    public ProductPriceHistoryEntity
    getLowestPrice(
            @PathVariable String productName
    ) {

        return priceAnalyticsService
                .getLowestPrice(productName);
    }

    @GetMapping("/{productName}/latest")
    public ProductPriceHistoryEntity
    getLatestPrice(
            @PathVariable String productName
    ) {

        return priceAnalyticsService
                .getLatestPrice(productName);
    }

    @GetMapping("/{productName}/average")
    public Double getAveragePrice(
            @PathVariable String productName
    ) {

        return priceAnalyticsService
                .getAveragePrice(productName);
    }

    @GetMapping("/{productName}/summary")
    public PriceSummaryDto
    getSummary(
            @PathVariable String productName
    ) {

        return priceAnalyticsService
                .getPriceSummary(productName);
    }

    @GetMapping("/{productName}/trend")
    public PriceTrendDto getTrend(
            @PathVariable String productName
    ) {

        return priceAnalyticsService
                .getPriceTrend(productName);
    }

    @GetMapping("/{productName}/recommendation")
    public BuyRecommendationDto
    getRecommendation(
            @PathVariable String productName
    ) {

        return priceAnalyticsService
                .getBuyRecommendation(
                        productName
                );
    }

    @GetMapping("/{productName}/analytics")
    public ProductAnalyticsDto getAnalytics(
            @PathVariable String productName
    ) {

        return priceAnalyticsService
                .getAnalytics(productName);
    }


    @GetMapping("/{productName}/compare")
    public ProductComparisonDto compare(
            @PathVariable String productName
    ) {

        return priceAnalyticsService
                .getProductComparison(
                        productName
                );
    }
}
