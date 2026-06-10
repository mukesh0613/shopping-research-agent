package com.mukesh.shoppingresearchagent.agent.graph;

import com.mukesh.shoppingresearchagent.agent.react.ReActContext;
import com.mukesh.shoppingresearchagent.service.PriceAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnalyticsNode implements AgentNode {

    private final
    PriceAnalyticsService
            priceAnalyticsService;

    @Override
    public String getNodeName() {

        return "AnalyticsNode";
    }

    @Override
    public void execute(
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

        context.getMemory().put(
                "recommendation",
                recommendation
        );
    }
}