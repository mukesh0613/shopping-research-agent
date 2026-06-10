package com.mukesh.shoppingresearchagent.agent.graph;

import com.mukesh.shoppingresearchagent.agent.react.ReActContext;
import com.mukesh.shoppingresearchagent.service.LLMProductExtractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductExtractionNode
        implements AgentNode {

    private final LLMProductExtractionService extractionService;

    @Override
    public String getNodeName() {
        return "ProductExtractionNode";
    }

    @Override
    public void execute(ReActContext context) {

        String product =
                extractionService
                        .extractProduct(
                                context.getQuery()
                        )
                        .getProductName();

        context.getMemory().put(
                "product",
                product
        );
    }
}