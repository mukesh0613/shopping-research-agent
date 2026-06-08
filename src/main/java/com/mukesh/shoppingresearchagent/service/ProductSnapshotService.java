package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.dto.StoreProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSnapshotService {
    private final PriceHistoryService priceHistoryService;
    public void saveSnapshots(List<StoreProductDto> products){
        for(StoreProductDto product:products){
            priceHistoryService.savePriceSnapshot(
                    product.getProductName(),
                    product.getSource(),
                    product.getPrice()
            );
        }
    }

}
