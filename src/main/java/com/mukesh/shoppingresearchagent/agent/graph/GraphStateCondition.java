package com.mukesh.shoppingresearchagent.agent.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphStateCondition {

    private String key;

    private String expectedValue;
}