package com.mukesh.shoppingresearchagent.agent.react;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToolObservation {
    private String toolName;
    private String result;
}
