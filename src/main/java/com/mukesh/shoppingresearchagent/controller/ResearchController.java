package com.mukesh.shoppingresearchagent.controller;

import com.mukesh.shoppingresearchagent.agent.cordinator.ShoppingCoordinatorAgent;
import com.mukesh.shoppingresearchagent.dto.ShoppingResearchRequest;
import com.mukesh.shoppingresearchagent.dto.ShoppingResearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/research")
@RequiredArgsConstructor
public class ResearchController {

    private final ShoppingCoordinatorAgent
            shoppingCoordinatorAgent;

    @PostMapping
    public ShoppingResearchResponse research(

            @RequestBody
            ShoppingResearchRequest request

    ) {

        return shoppingCoordinatorAgent
                .researchProduct(
                        request.getQuery()
                );
    }
}