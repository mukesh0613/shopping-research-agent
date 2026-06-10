package com.mukesh.shoppingresearchagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ShoppingResearchAgentApplication {

    public static void main(
            String[] args
    ) {

        SpringApplication.run(
                ShoppingResearchAgentApplication.class,
                args
        );
    }
}