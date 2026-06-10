package com.mukesh.shoppingresearchagent.agent.steps;

import com.mukesh.shoppingresearchagent.agent.AgentContext;
import com.mukesh.shoppingresearchagent.agent.AgentStep;
import com.mukesh.shoppingresearchagent.dto.ProductAnalyticsDto;
import com.mukesh.shoppingresearchagent.service.PriceAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(2)
public class AnalyticsStep implements AgentStep {
    private final
    PriceAnalyticsService
            analyticsService;

    @Override
    public void execute(
            AgentContext context
    ) {

        ProductAnalyticsDto analytics =
                analyticsService.getAnalytics(
                        context.getProductName()
                );

        context.setRecommendation(
                analytics.getRecommendation()
        );
    }

}
