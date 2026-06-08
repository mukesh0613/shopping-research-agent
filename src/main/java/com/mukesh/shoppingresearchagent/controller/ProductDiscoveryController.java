package com.mukesh.shoppingresearchagent.controller;

import com.mukesh.shoppingresearchagent.dto.ComparisonResultDto;
import com.mukesh.shoppingresearchagent.dto.StoreProductDto;
import com.mukesh.shoppingresearchagent.service.ProductDiscoveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/discovery")
@RequiredArgsConstructor
public class ProductDiscoveryController {
    private final ProductDiscoveryService productDiscoveryService;

    @GetMapping
    public List<StoreProductDto> discover(@RequestParam String query){
        return  productDiscoveryService.discoverproducts(query);
    }

    @GetMapping("/compare")
    public ComparisonResultDto compare(
            @RequestParam String query
    ) {

        return productDiscoveryService
                .compareProducts(query);
    }
}
