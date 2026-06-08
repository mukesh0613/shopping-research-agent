package com.mukesh.shoppingresearchagent.controller;

import com.mukesh.shoppingresearchagent.enums.ResearchIntent;
import com.mukesh.shoppingresearchagent.service.IntentRouterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IntentTestController {
    private final IntentRouterService intentRouterService;

    @GetMapping("/intent")
    public ResearchIntent detectIntent(
            @RequestParam String query
    ) {

        return intentRouterService
                .detectIntent(query);
    }
}
