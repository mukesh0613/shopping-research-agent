package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.enums.ResearchIntent;
import org.springframework.stereotype.Service;

@Service
public class IntentRouterService {
    public ResearchIntent detectIntent(String query){
        String q=query.toLowerCase();
        if(q.contains("where should i buy")
                || q.contains("compare")
                || q.contains("cheapest")
                || q.contains("best price")) {

            return ResearchIntent.PRICE_COMPARISON;
        }

        if(q.contains("should i buy")
                || q.contains("buy now")
                || q.contains("good time to buy")) {

            return ResearchIntent.BUY_RECOMMENDATION;
        }

        return ResearchIntent.GENERAL_RESEARCH;
    }
}
