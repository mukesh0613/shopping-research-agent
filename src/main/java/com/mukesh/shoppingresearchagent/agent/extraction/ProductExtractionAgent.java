package com.mukesh.shoppingresearchagent.agent.extraction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mukesh.shoppingresearchagent.dto.ProductExtraction;
import com.mukesh.shoppingresearchagent.service.GroqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductExtractionAgent {

    private final GroqService groqService;

    private final ObjectMapper objectMapper;

    public ProductExtraction extract(
            String query
    ) {

        try {

            String prompt =
                    buildPrompt(query);

            String response =
                    groqService.generateContent(
                            prompt
                    );

            log.info(
                    "LLM Response: {}",
                    response
            );

            response =
                    response
                            .replace("```json", "")
                            .replace("```", "")
                            .trim();

            return objectMapper.readValue(
                    response,
                    ProductExtraction.class
            );

        } catch (Exception ex) {

            log.error(
                    "Product extraction failed",
                    ex
            );

            throw new RuntimeException(
                    "Failed to extract product information",
                    ex
            );
        }
    }

    private String buildPrompt(
            String query
    ) {

        return """
                Extract shopping information from the user query.

                Return ONLY valid JSON.

                Do NOT wrap the response in markdown.

                Do NOT use ```json.

                Example:

                {
                  "productName":"Peanut Butter",
                  "category":"Nutrition",
                  "budget":500
                }

                User Query:
                %s
                """
                .formatted(query);
    }
}