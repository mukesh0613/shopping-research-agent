package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.dto.StoreProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockZeptoService {
    public List<StoreProductDto> searchProducts(String query){
        return  List.of(StoreProductDto.builder()
                .productName("Pintola Peanut Butter")
                .source("Zepto")
                .price(355.0)
                .rating(4.4)
                .build());
    }
}
