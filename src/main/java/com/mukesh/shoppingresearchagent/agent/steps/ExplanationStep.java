package com.mukesh.shoppingresearchagent.agent.steps;

import com.mukesh.shoppingresearchagent.agent.AgentContext;
import com.mukesh.shoppingresearchagent.agent.AgentStep;
import com.mukesh.shoppingresearchagent.service.GroqService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(3)
public class ExplanationStep implements AgentStep {
    private final GroqService groqService;

    @Override
    public void execute(
            AgentContext context
    ) {

        String prompt = """
                Product: %s

                Recommendation: %s

                Explain whether user should buy.
                """
                .formatted(
                        context.getProductName(),
                        context.getRecommendation()
                );

        String report =
                groqService.generateContent(
                        prompt
                );

        context.setReport(report);
    }
}
