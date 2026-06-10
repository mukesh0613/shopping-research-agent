package com.mukesh.shoppingresearchagent.agent.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeEdge {

    private String fromNode;

    private String toNode;

    private GraphStateCondition condition;
}