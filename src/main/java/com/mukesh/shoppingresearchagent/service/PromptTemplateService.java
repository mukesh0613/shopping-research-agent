package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.dto.ComparisonResultDto;
import com.mukesh.shoppingresearchagent.dto.ProductAnalyticsDto;
import com.mukesh.shoppingresearchagent.dto.research.ProductInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptTemplateService {
    public String buildShoppingPrompt(String query, List<ProductInfo> products){
        StringBuilder productContext=new StringBuilder();

        for(ProductInfo product:products){
            productContext.append("""
                
                Product Name: %s
                Price: %.2f
                Description: %s
                
                """
                    .formatted(
                            product.getName(),
                            product.getPrice(),
                            product.getDescription()
                    ));
        }
        return """
                You are a shopping research assistant.
                
                                                          IMPORTANT:
                                                          You MUST ONLY use the products provided below.
                                                          Do NOT recommend any product that is not listed.
                                                          Do NOT use your own knowledge.
                
                                                          User Query:
                                                          %s
                
                                                          Retrieved Products:
                                                          %s
                
                                                          Using ONLY the retrieved products:
                
                                                          1. Recommend the best product
                                                          2. Explain why
                                                          3. Compare all products
                                                          4. Give a final ranking
               Return the answer in Markdown
                """
                .formatted(query, productContext.toString());
    }



    public String buildComparisonPrompt(
            String query,
            ComparisonResultDto comparison
    ) {

        return """
            You are a shopping assistant.

            User Query:
            %s

            Product Name:
            %s

            Best Store:
            %s

            Best Price:
            %.2f

            Store Rating:
            %.1f

            Explain clearly why this store is the best choice.
            Keep the answer concise and practical.
            """
                .formatted(
                        query,
                        comparison.getProductName(),
                        comparison.getBestStore(),
                        comparison.getBestPrice(),
                        comparison.getBestRating()
                );
    }

    public String buildAnalyticsPrompt(
            String query,
            ProductAnalyticsDto analytics
    ) {

        return """
            You are a shopping assistant.

            User Query:
            %s

            Product Name:
            %s

            Latest Price:
            %.2f

            Lowest Historical Price:
            %.2f

            Average Historical Price:
            %.2f

            Recommendation:
            %s

            Explain to the user whether this is a good time to buy.
            Give a concise and practical answer.
            """
                .formatted(
                        query,
                        analytics.getProductName(),
                        analytics.getLatestPrice(),
                        analytics.getLowestPrice(),
                        analytics.getAveragePrice(),
                        analytics.getRecommendation()
                );
    }


    public String buildProductExtractionPrompt(
            String query
    ) {

        return """
            Extract only the product name from the query.

            Query:
            %s

            Rules:
            - Return only the product name
            - No explanation
            - No extra text

            Product:
            """
                .formatted(query);
    }
}
