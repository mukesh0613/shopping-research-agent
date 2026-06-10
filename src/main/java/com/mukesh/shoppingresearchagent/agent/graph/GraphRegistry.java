package com.mukesh.shoppingresearchagent.agent.graph;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GraphRegistry {

    private final Map<String, AgentNode>
            nodeMap =
            new HashMap<>();

    public void registerNode(
            AgentNode node
    ) {

        nodeMap.put(
                node.getNodeName(),
                node
        );
    }

    public AgentNode getNode(
            String nodeName
    ) {

        return nodeMap.get(
                nodeName
        );
    }
}