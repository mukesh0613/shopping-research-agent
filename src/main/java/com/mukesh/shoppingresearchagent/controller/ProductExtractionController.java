package com.mukesh.shoppingresearchagent.controller;

import com.mukesh.shoppingresearchagent.dto.research.ProductExtractionResponse;
import com.mukesh.shoppingresearchagent.service.LLMProductExtractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/extract")
public class ProductExtractionController {

    private final LLMProductExtractionService
            extractionService;

    @GetMapping
    public ProductExtractionResponse extract(
            @RequestParam String query
    ) {

        return extractionService
                .extractProduct(query);
    }
}
