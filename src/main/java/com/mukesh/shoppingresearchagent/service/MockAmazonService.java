package com.mukesh.shoppingresearchagent.service;


import com.mukesh.shoppingresearchagent.dto.StoreProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockAmazonService {
    public List<StoreProductDto> searchProducts(String query){
        return  List.of(StoreProductDto.builder()
                .productName("Pintola Peanut Butter")
                .source("Amazon")
                .price(349.0)
                .rating(4.5)
                .build());
    }

}
