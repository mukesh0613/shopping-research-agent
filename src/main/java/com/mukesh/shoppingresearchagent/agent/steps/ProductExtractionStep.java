package com.mukesh.shoppingresearchagent.agent.steps;

import com.mukesh.shoppingresearchagent.agent.AgentContext;
import com.mukesh.shoppingresearchagent.agent.AgentStep;
import com.mukesh.shoppingresearchagent.service.LLMProductExtractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(1)
public class ProductExtractionStep implements AgentStep {
    private final LLMProductExtractionService llmProductExtractionService;

    @Override
    public void execute(
            AgentContext context
    ) {

        String product =
                llmProductExtractionService
                        .extractProduct(
                                context.getQuery()
                        )
                        .getProductName();

        context.setProductName(product);
    }
}
