package com.mukesh.shoppingresearchagent.agent.react;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionRecord {

    private String toolName;

    private String input;

    private String output;

    private String status;

    private long executionTimeMs;
}