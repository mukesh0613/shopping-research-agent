package com.mukesh.shoppingresearchagent.agent;

import com.mukesh.shoppingresearchagent.dto.ProductAnalyticsDto;
import com.mukesh.shoppingresearchagent.dto.research.ProductExtractionResponse;
import com.mukesh.shoppingresearchagent.service.LLMProductExtractionService;
import com.mukesh.shoppingresearchagent.service.PriceAnalyticsService;
import com.mukesh.shoppingresearchagent.service.PromptTemplateService;
import com.mukesh.shoppingresearchagent.service.GroqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuyRecommendationTool implements ResearchTool{
    private final LLMProductExtractionService llmProductExtractionService;
    private final PriceAnalyticsService priceAnalyticsService;
    private final PromptTemplateService promptTemplateService;
    private final GroqService groqService;

    @Override
    public String getToolName() {
        return "BUY_RECOMMENDATION";
    }

    @Override
    public String execute(String query) {

        ProductExtractionResponse product =
                llmProductExtractionService
                        .extractProduct(query);

        ProductAnalyticsDto analytics =
                priceAnalyticsService
                        .getAnalytics(
                                product.getProductName()
                        );

        String prompt =
                promptTemplateService
                        .buildAnalyticsPrompt(
                                query,
                                analytics
                        );

        return groqService.generateContent(prompt);
    }

}
