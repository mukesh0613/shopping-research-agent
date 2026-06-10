/*package com.mukesh.shoppingresearchagent.agent.react;

import com.mukesh.shoppingresearchagent.service.LLMProductExtractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductExtractionTool implements AgentTool {
    private final LLMProductExtractionService extractionService;

    @Override
    public String getToolName() {

        return "ProductExtractionTool";
    }

    @Override
    public ToolResult execute(
            String input
    ) {
        String product =
                extractionService
                        .extractProduct(input)
                        .getProductName();

        return new ToolResult(
                getToolName(),
                product
        );
    }

    @Override
    public String getOutputKey() {

        return "product";
    }

    @Override
    public String getInputKey() {

        return "query";
    }
}*/
