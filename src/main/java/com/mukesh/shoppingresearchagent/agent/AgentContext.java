package com.mukesh.shoppingresearchagent.agent;

import lombok.Data;

@Data
public class AgentContext {
    private String query;

    private String productName;

    private String recommendation;

    private String report;
}
