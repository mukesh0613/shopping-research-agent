package com.mukesh.shoppingresearchagent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ApiClientService {

    private final RestClient restClient;

    public String get(
            String url
    ) {

        return restClient
                .get()
                .uri(url)
                .retrieve()
                .body(String.class);
    }
}