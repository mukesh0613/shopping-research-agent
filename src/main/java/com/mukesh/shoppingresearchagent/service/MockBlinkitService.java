package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.dto.StoreProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockBlinkitService {
    public List<StoreProductDto> searchProducts(String query){
        return  List.of(StoreProductDto.builder()
                .productName("Pintola Peanut Butter")
                .source("BlinkIt")
                .price(329.0)
                .rating(4.7)
                .build());
    }
}
