package com.mukesh.shoppingresearchagent.agent.react;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentError {

    private String toolName;

    private String errorMessage;
}