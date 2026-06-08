package com.mukesh.shoppingresearchagent.agent;

import com.mukesh.shoppingresearchagent.dto.ComparisonResultDto;
import com.mukesh.shoppingresearchagent.service.GroqService;
import com.mukesh.shoppingresearchagent.service.ProductDiscoveryService;
import com.mukesh.shoppingresearchagent.service.PromptTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StoreComparisonTool implements ResearchTool{
    private final ProductDiscoveryService
            discoveryService;

    private final PromptTemplateService
            promptTemplateService;

    private final GroqService
            groqService;

    @Override
    public String getToolName() {
        return "PRICE_COMPARISON";
    }

    @Override
    public String execute(String query) {

        ComparisonResultDto comparison =
                discoveryService
                        .compareProducts(query);

        String prompt =
                promptTemplateService
                        .buildComparisonPrompt(
                                query,
                                comparison
                        );

        return groqService
                .generateContent(prompt);
    }
}
