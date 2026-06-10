package com.mukesh.shoppingresearchagent.agent.react;

import com.mukesh.shoppingresearchagent.service.PriceAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnalyticsTool
        implements AgentTool {

    private final
    PriceAnalyticsService
            priceAnalyticsService;

    @Override
    public String getToolName() {

        return "AnalyticsTool";
    }

    @Override
    public String getInputKey() {

        return "product";
    }

    @Override
    public String getOutputKey() {

        return "recommendation";
    }

    @Override
    public ToolResult execute(
            ReActContext context
    ) {

        String product =
                (String) context
                        .getMemory()
                        .get("product");

        String recommendation =
                priceAnalyticsService
                        .getAnalytics(product)
                        .getRecommendation();

        return new ToolResult(
                getToolName(),
                recommendation
        );
    }
}