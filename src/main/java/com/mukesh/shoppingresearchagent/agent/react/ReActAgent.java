package com.mukesh.shoppingresearchagent.agent.react;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReActAgent {

    private static final int MAX_ITERATIONS = 5;

    private final ReActToolRegistry reActToolRegistry;
    private final AgentPlanner agentPlanner;
    private final FinalResponseGenerator finalResponseGenerator;

    public String execute(
            String query
    ) {

        ReActContext context =
                new ReActContext();

        context.setQuery(query);

        int iteration = 0;

        while (iteration < MAX_ITERATIONS) {

            AgentPlan plan =
                    agentPlanner.plan(context);

            context.getThoughts().add(
                    new AgentThought(
                            plan.getThought()
                    )
            );

            if (plan.getActions() == null
                    || plan.getActions().isEmpty()) {

                break;
            }

            boolean finishRequested = false;

            for (String action
                    : plan.getActions()) {

                if ("FINISH".equalsIgnoreCase(
                        action
                )) {

                    finishRequested = true;
                    break;
                }

                AgentTool tool =
                        reActToolRegistry.getTool(
                                action
                        );

                if (tool == null) {

                    context.getErrors().add(
                            new AgentError(
                                    action,
                                    "Tool not found"
                            )
                    );

                    continue;
                }

                try {

                    long startTime =
                            System.currentTimeMillis();

                    ToolResult result =
                            tool.execute(context);

                    long endTime =
                            System.currentTimeMillis();

                    context.getObservations().add(
                            new ToolObservation(
                                    result.getToolName(),
                                    result.getOutput()
                            )
                    );

                    context.getMemory().put(
                            tool.getOutputKey(),
                            result.getOutput()
                    );

                    context.getExecutionHistory().add(
                            new ExecutionRecord(
                                    tool.getToolName(),
                                    context.getMemory().toString(),
                                    result.getOutput(),
                                    "SUCCESS",
                                    endTime - startTime
                            )
                    );

                } catch (Exception ex) {

                    context.getErrors().add(
                            new AgentError(
                                    tool.getToolName(),
                                    ex.getMessage()
                            )
                    );

                    context.getExecutionHistory().add(
                            new ExecutionRecord(
                                    tool.getToolName(),
                                    context.getMemory().toString(),
                                    ex.getMessage(),
                                    "FAILED",
                                    0
                            )
                    );
                }
            }

            if (finishRequested) {

                break;
            }

            iteration++;
        }

        String answer =
                finalResponseGenerator
                        .generateFinalResponse(
                                context
                        );

        context.setFinalAnswer(
                answer
        );

        return context.getFinalAnswer();
    }
}