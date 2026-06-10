package com.mukesh.shoppingresearchagent.agent.recommendation;

import com.mukesh.shoppingresearchagent.dto.PriceAnalysisResult;
import com.mukesh.shoppingresearchagent.dto.ProductSearchResult;
import com.mukesh.shoppingresearchagent.dto.ReviewAnalysisResult;
import com.mukesh.shoppingresearchagent.service.GroqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationAgent {

    private final GroqService groqService;

    public String generateRecommendation(

            List<ProductSearchResult> products,

            PriceAnalysisResult priceAnalysis,

            ReviewAnalysisResult reviewAnalysis

    ) {

        if (!priceAnalysis.isProductsFound()) {

            return """
                    No products found matching
                    your search criteria.

                    Try:

                    1. Increasing your budget
                    2. Using a broader product name
                    3. Removing strict filters
                    """;
        }

        String prompt =
                buildPrompt(
                        products,
                        priceAnalysis,
                        reviewAnalysis
                );

        return groqService
                .generateContent(
                        prompt
                );
    }

    private String buildPrompt(

            List<ProductSearchResult> products,

            PriceAnalysisResult priceAnalysis,

            ReviewAnalysisResult reviewAnalysis

    ) {

        StringBuilder prompt =
                new StringBuilder();

        prompt.append(
                """
                You are an expert shopping advisor.

                Analyze pricing information,
                review analysis and product data.

                Recommend the best option.

                PRICE ANALYSIS

                """
        );

        prompt.append(
                String.format(
                        """
                        Best Store: %s
                        Lowest Price: %.2f
                        Highest Price: %.2f
                        Average Price: %.2f
                        Savings: %.2f

                        """,

                        priceAnalysis.getBestStore(),

                        priceAnalysis.getLowestPrice(),

                        priceAnalysis.getHighestPrice(),

                        priceAnalysis.getAveragePrice(),

                        priceAnalysis.getSavings()
                )
        );

        prompt.append(
                """
                REVIEW ANALYSIS

                """
        );

        prompt.append(
                String.format(
                        """
                        Average Rating: %.1f
                        Sentiment: %s
                        Strengths: %s
                        Weaknesses: %s
                        Summary: %s

                        """,

                        reviewAnalysis.getAverageRating(),

                        reviewAnalysis.getSentiment(),

                        reviewAnalysis.getStrengths(),

                        reviewAnalysis.getWeaknesses(),

                        reviewAnalysis.getSummary()
                )
        );

        prompt.append(
                """
                PRODUCT DETAILS

                """
        );

        for (ProductSearchResult product
                : products) {

            prompt.append(
                    String.format(
                            """
                            Store: %s
                            Product: %s
                            Price: %.2f
                            Rating: %.1f

                            """,

                            product.getStore(),

                            product.getProductName(),

                            product.getPrice(),

                            product.getRating()
                    )
            );
        }

        prompt.append(
                """

                Provide:

                1. Best Store
                2. Reason
                3. Price Analysis
                4. Review Analysis
                5. Final Recommendation

                """
        );

        return prompt.toString();
    }
}