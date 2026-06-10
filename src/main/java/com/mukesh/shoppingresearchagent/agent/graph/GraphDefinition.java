package com.mukesh.shoppingresearchagent.agent.graph;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GraphDefinition {

    private List<AgentNode> nodes =
            new ArrayList<>();

    private List<NodeEdge> edges =
            new ArrayList<>();
}