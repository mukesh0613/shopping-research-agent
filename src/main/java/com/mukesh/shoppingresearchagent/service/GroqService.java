package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.config.GroqConfig;
import com.mukesh.shoppingresearchagent.dto.groq.GroqRequest;
import com.mukesh.shoppingresearchagent.dto.groq.Message;
import com.mukesh.shoppingresearchagent.dto.groq.response.GroqResponse;
import com.mukesh.shoppingresearchagent.exception.GeminiServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroqService {

    private final RestClient restClient;
    private final GroqConfig groqConfig;

    public String generateContent(String prompt) {

        try {

            Message message =
                    new Message(
                            "user",
                            prompt
                    );

            GroqRequest request =
                    new GroqRequest(
                            "llama-3.3-70b-versatile",
                            List.of(message)
                    );

            String url =
                    "https://api.groq.com/openai/v1/chat/completions";

            GroqResponse response =
                    restClient.post()
                            .uri(url)
                            .header(
                                    "Authorization",
                                    "Bearer " + groqConfig.getApiKey()
                            )
                            .body(request)
                            .retrieve()
                            .body(GroqResponse.class);

            return response.getChoices()
                    .get(0)
                    .getMessage()
                    .getContent();

        } catch (Exception e) {

            log.error("Groq API Error", e);

            throw new GeminiServiceException(
                    "Groq service unavailable. Please try again later."
            );
        }
    }
}