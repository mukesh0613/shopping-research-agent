package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.dto.ResearchResponseDto;
import com.mukesh.shoppingresearchagent.dto.research.ProductInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResearchService {

    private final GroqService groqService;
    private final PromptTemplateService promptTemplateService;
    private final ProductSearchService productSearchService;

    public ResearchResponseDto research(String query){
        List<ProductInfo> products;
        products = productSearchService.searchProducts(query);
        String prompt=promptTemplateService.buildShoppingPrompt(query,products);
        System.out.println(prompt);
        String report= groqService.generateContent(prompt);

        return new ResearchResponseDto(query, report);
    }
}
