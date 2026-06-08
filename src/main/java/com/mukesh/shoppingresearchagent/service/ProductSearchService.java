package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.dto.research.ProductInfo;
import com.mukesh.shoppingresearchagent.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class ProductSearchService {

    public final List<ProductInfo> catalog=List.of(
            new ProductInfo(
                    "Acer Nitro V",
                    74999.0,
                    "RTX 3050 graphics, 16GB RAM, Intel i5 processor",
                    "gaming laptop"
            ),

            new ProductInfo(
                    "ASUS TUF Gaming F15",
                    79999.0,
                    "RTX graphics card, Ryzen processor, 144Hz display",
                    "gaming laptop"
            ),

            new ProductInfo(
                    "Lenovo Legion 5",
                    84999.0,
                    "High-performance gaming laptop with RTX graphics",
                    "gaming laptop"
            ),

            new ProductInfo(
                    "Lenovo IdeaPad Slim 3",
                    54999.0,
                    "Lightweight productivity laptop with Ryzen 7 processor",
                    "office laptop"
            ),

            new ProductInfo(
                    "HP 15",
                    52999.0,
                    "Intel i5 processor, 16GB RAM, SSD storage",
                    "office laptop"
            ),

            new ProductInfo(
                    "Dell Inspiron 15",
                    56999.0,
                    "Reliable laptop for office work and students",
                    "office laptop"
            ),

            new ProductInfo(
                    "MacBook Air M2",
                    99999.0,
                    "Apple M2 chip, premium ultrabook for professionals",
                    "ultrabook"
            ),

            new ProductInfo(
                    "Samsung Galaxy S24",
                    74999.0,
                    "Flagship Android smartphone with excellent camera",
                    "smartphone"
            ),

            new ProductInfo(
                    "iPhone 15",
                    69999.0,
                    "Apple smartphone with A16 chip and great camera",
                    "smartphone"
            ),

            new ProductInfo(
                    "Google Pixel 8",
                    64999.0,
                    "Best smartphone for photography and AI features",
                    "smartphone"
            ),

            new ProductInfo(
                    "OnePlus 12",
                    62999.0,
                    "Fast performance and flagship camera setup",
                    "smartphone"
            ),

            new ProductInfo(
                    "Sony WH-1000XM5",
                    29999.0,
                    "Premium noise-cancelling wireless headphones",
                    "headphones"
            ),

            new ProductInfo(
                    "JBL Tune 770NC",
                    7999.0,
                    "Affordable wireless headphones with ANC",
                    "headphones"
            ),

            new ProductInfo(
                    "Samsung Galaxy Tab S9",
                    64999.0,
                    "Premium Android tablet for productivity and entertainment",
                    "tablet"
            ),

            new ProductInfo(
                    "iPad Air",
                    59999.0,
                    "Apple tablet powered by M1 chip",
                    "tablet"
            )
    );

    public List<ProductInfo> searchProducts(String query) {
        List<String> keywords= Arrays.stream(query.toLowerCase().split("\\s+")).toList();
        /*System.out.println(keywords);

        System.out.println(
                CalculateScore(
                        catalog.get(0),
                        keywords
                )
        );*/

        return catalog.stream()
                .filter(product -> CalculateScore(product, keywords)>0)
                .sorted((p1,p2)->Integer.compare(
                CalculateScore(p2,keywords),
                CalculateScore(p1,keywords)
        ))
                .limit(3)
                .toList();
    }

    private int CalculateScore (ProductInfo product, List<String> keywords){
        String searchableText=(
                product.getName()+" "+product.getDescription()+" "+product.getCategory()+" "
                ).toLowerCase();

        int score=0;

        for(String keyword:keywords){
            if(searchableText.contains(keyword)){
                score++;
            }
        }

        return score;
    }
}