package com.mukesh.shoppingresearchagent.agent.graph;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CycleDetector {

    public boolean hasCycle(
            GraphDefinition graph
    ) {

        Set<String> visited =
                new HashSet<>();

        Set<String> path =
                new HashSet<>();

        for (AgentNode node
                : graph.getNodes()) {

            if (dfs(
                    node.getNodeName(),
                    graph,
                    visited,
                    path
            )) {

                return true;
            }
        }

        return false;
    }

    private boolean dfs(

            String currentNode,

            GraphDefinition graph,

            Set<String> visited,

            Set<String> path

    ) {

        if (path.contains(
                currentNode
        )) {

            return true;
        }

        if (visited.contains(
                currentNode
        )) {

            return false;
        }

        visited.add(
                currentNode
        );

        path.add(
                currentNode
        );

        for (NodeEdge edge
                : graph.getEdges()) {

            if (edge.getFromNode()
                    .equalsIgnoreCase(
                            currentNode
                    )) {

                if (dfs(
                        edge.getToNode(),
                        graph,
                        visited,
                        path
                )) {

                    return true;
                }
            }
        }

        path.remove(
                currentNode
        );

        return false;
    }
}