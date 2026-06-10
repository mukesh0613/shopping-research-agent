package com.mukesh.shoppingresearchagent.agent.graph;

import com.mukesh.shoppingresearchagent.agent.react.ReActContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class GraphRunner {

    private final GraphRegistry graphRegistry;

    private final GraphValidator graphValidator;

    private final GraphConditionEvaluator conditionEvaluator;

    private final CheckpointManager checkpointManager;

    public void execute(

            GraphDefinition graph,

            ReActContext context

    ) {

        graphValidator.validate(
                graph
        );

        String currentNode;

        if (checkpointManager.exists()) {

            ExecutionCheckpoint checkpoint =
                    checkpointManager.load();

            currentNode =
                    checkpoint.getCurrentNode();

            context.setMemory(
                    new HashMap<>(
                            checkpoint.getMemory()
                    )
            );

            System.out.println(
                    "Resuming workflow from : "
                            + currentNode
            );

        } else {

            currentNode =
                    findStartNode(
                            graph
                    );
        }

        while (currentNode != null) {

            AgentNode node =
                    graphRegistry.getNode(
                            currentNode
                    );

            if (node == null) {

                throw new RuntimeException(
                        "Node not found : "
                                + currentNode
                );
            }

            try {

                node.execute(
                        context
                );

                if ("END".equalsIgnoreCase(
                        currentNode
                )) {

                    checkpointManager.clear();

                    System.out.println(
                            "Workflow completed"
                    );

                    break;
                }

                String nextNode =
                        findNextNode(
                                graph,
                                currentNode,
                                context
                        );

                saveCheckpoint(
                        nextNode,
                        context
                );

                currentNode =
                        nextNode;

            } catch (Exception ex) {

                System.out.println(
                        "Workflow failed at node : "
                                + currentNode
                );

                throw ex;
            }
        }
    }

    private void saveCheckpoint(

            String nextNode,

            ReActContext context

    ) {

        ExecutionCheckpoint checkpoint =
                new ExecutionCheckpoint();

        checkpoint.setCurrentNode(
                nextNode
        );

        checkpoint.setMemory(
                new HashMap<>(
                        context.getMemory()
                )
        );

        checkpoint.setTimestamp(
                LocalDateTime.now()
        );

        checkpointManager.save(
                checkpoint
        );
    }

    private String findStartNode(
            GraphDefinition graph
    ) {

        for (NodeEdge edge
                : graph.getEdges()) {

            if ("START".equalsIgnoreCase(
                    edge.getFromNode()
            )) {

                return edge.getToNode();
            }
        }

        throw new RuntimeException(
                "START node not found"
        );
    }

    private String findNextNode(

            GraphDefinition graph,

            String currentNode,

            ReActContext context

    ) {

        for (NodeEdge edge
                : graph.getEdges()) {

            boolean currentNodeMatched =
                    edge.getFromNode()
                            .equalsIgnoreCase(
                                    currentNode
                            );

            boolean conditionMatched =
                    conditionEvaluator
                            .evaluate(
                                    edge.getCondition(),
                                    context
                            );

            if (
                    currentNodeMatched
                            &&
                            conditionMatched
            ) {

                return edge.getToNode();
            }
        }

        return null;
    }
}