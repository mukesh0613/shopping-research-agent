package com.mukesh.shoppingresearchagent.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Getter
@Configuration
public class GroqConfig {
    @Value("${groq.api.key}")
    private String apiKey;

}
