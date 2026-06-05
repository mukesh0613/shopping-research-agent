package com.mukesh.shoppingresearchagent.service;

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
}
