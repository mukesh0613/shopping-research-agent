package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.dto.PriceSummaryDto;
import com.mukesh.shoppingresearchagent.dto.PriceTrendDto;
import com.mukesh.shoppingresearchagent.exception.PriceHistoryNotFoundException;
import com.mukesh.shoppingresearchagent.repository.ProductPriceHistoryRepository;
import com.mukesh.shoppingresearchagent.entity.ProductPriceHistoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceHistoryService {
    private final ProductPriceHistoryRepository productPriceHistoryRepository;

    public void savePriceSnapshot(
        String productName,
        String source,
        Double price
    ){
        ProductPriceHistoryEntity entity=ProductPriceHistoryEntity.builder()
                .productName(productName)
                .source(source)
                .price(price)
                .capturedAt(LocalDateTime.now())
                .build();

        productPriceHistoryRepository.save(entity);
    }

    public List<ProductPriceHistoryEntity>
    getPriceHistory(String productName) {

        return productPriceHistoryRepository.findByProductName(productName);
    }


}

