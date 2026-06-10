package com.mukesh.shoppingresearchagent.agent.graph;

import org.springframework.stereotype.Component;

@Component
public class GraphValidator {

    public void validate(
            GraphDefinition graph
    ) {

        validateNodes(graph);

        validateStartNode(graph);

        validateEndNode(graph);

        validateEdges(graph);
    }

    private void validateNodes(
            GraphDefinition graph
    ) {

        if (graph.getNodes().isEmpty()) {

            throw new RuntimeException(
                    "Graph contains no nodes"
            );
        }
    }

    private void validateStartNode(
            GraphDefinition graph
    ) {

        boolean found = graph
                .getNodes()
                .stream()
                .anyMatch(
                        node ->
                                "START"
                                        .equalsIgnoreCase(
                                                node.getNodeName()
                                        )
                );

        if (!found) {

            throw new RuntimeException(
                    "START node missing"
            );
        }
    }

    private void validateEndNode(
            GraphDefinition graph
    ) {

        boolean found = graph
                .getNodes()
                .stream()
                .anyMatch(
                        node ->
                                "END"
                                        .equalsIgnoreCase(
                                                node.getNodeName()
                                        )
                );

        if (!found) {

            throw new RuntimeException(
                    "END node missing"
            );
        }
    }

    private void validateEdges(
            GraphDefinition graph
    ) {

        for (NodeEdge edge
                : graph.getEdges()) {

            boolean targetExists =
                    graph.getNodes()
                            .stream()
                            .anyMatch(
                                    node ->
                                            node.getNodeName()
                                                    .equalsIgnoreCase(
                                                            edge.getToNode()
                                                    )
                            );

            if (!targetExists) {

                throw new RuntimeException(
                        "Invalid edge target: "
                                + edge.getToNode()
                );
            }
        }
    }
}