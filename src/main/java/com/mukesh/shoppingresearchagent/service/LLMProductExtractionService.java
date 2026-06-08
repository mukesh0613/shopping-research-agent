package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.dto.research.ProductExtractionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LLMProductExtractionService {
    private final GroqService groqService;
    private final PromptTemplateService promptTemplateService;

    public ProductExtractionResponse extractProduct(String query){
        String prompt=promptTemplateService.buildProductExtractionPrompt(query);
        String productName=groqService.generateContent(prompt);
        return new ProductExtractionResponse(productName.trim());
    }

}
