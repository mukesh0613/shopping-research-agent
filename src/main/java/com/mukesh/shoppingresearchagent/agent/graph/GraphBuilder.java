package com.mukesh.shoppingresearchagent.agent.graph;

public class GraphBuilder {

    private final GraphDefinition graph =
            new GraphDefinition();

    public GraphBuilder addNode(
            AgentNode node
    ) {

        graph.getNodes().add(
                node
        );

        return this;
    }

    public GraphBuilder addEdge(

            String from,

            String to

    ) {

        graph.getEdges().add(

                new NodeEdge(
                        from,
                        to,
                        null
                )
        );

        return this;
    }

    public GraphBuilder addConditionalEdge(

            String from,

            String to,

            GraphStateCondition condition

    ) {

        graph.getEdges().add(

                new NodeEdge(
                        from,
                        to,
                        condition
                )
        );

        return this;
    }

    public GraphDefinition build() {

        return graph;
    }
}