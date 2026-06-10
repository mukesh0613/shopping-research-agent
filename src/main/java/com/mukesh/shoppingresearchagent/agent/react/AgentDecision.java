package com.mukesh.shoppingresearchagent.agent.react;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentDecision {
    private String thought;
    private String action;
}

