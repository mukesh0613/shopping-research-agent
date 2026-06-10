package com.mukesh.shoppingresearchagent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class AsyncConfig {

    @Bean
    public ExecutorService providerExecutor() {

        return Executors.newFixedThreadPool(
                10
        );
    }
}