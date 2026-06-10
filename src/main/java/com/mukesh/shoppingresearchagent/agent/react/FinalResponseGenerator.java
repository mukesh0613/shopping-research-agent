package com.mukesh.shoppingresearchagent.agent.react;

import com.mukesh.shoppingresearchagent.service.GroqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinalResponseGenerator {

    private final GroqService groqService;

    public String generateFinalResponse(
            ReActContext context
    ) {

        String prompt = """
                You are an AI assistant.

                User Query:
                %s

                Agent Memory:
                %s

                Generate a final helpful response.
                """
                .formatted(
                        context.getQuery(),
                        context.getMemory()
                );

        return groqService.generateContent(
                prompt
        );
    }
}