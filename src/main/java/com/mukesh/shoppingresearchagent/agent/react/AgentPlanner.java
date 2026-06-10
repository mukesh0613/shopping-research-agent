package com.mukesh.shoppingresearchagent.agent.react;

import com.mukesh.shoppingresearchagent.service.GroqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AgentPlanner {

    private final GroqService groqService;

    public AgentPlan plan(
            ReActContext context
    ) {

        String observationContext =
                buildObservationContext(
                        context
                );

        String errorContext =
                buildErrorContext(
                        context
                );

        String prompt = """
You are an AI workflow planner.

User Query:
%s

Previous Observations:
%s

Previous Errors:
%s

Available Actions:

ProductExtractionTool
AnalyticsTool
BackupAnalyticsTool
FINISH

Rules:
- Choose ProductExtractionTool if product is unknown
- Choose AnalyticsTool if product is known but recommendation is missing
- If AnalyticsTool previously failed, use BackupAnalyticsTool
- Choose FINISH if enough information has been collected

Return exactly:

THOUGHT=<thought>

ACTIONS=

<tool1>
<tool2>
<tool3>
"""
                .formatted(
                        context.getQuery(),
                        observationContext,
                        errorContext
                );

        String response =
                groqService.generateContent(
                        prompt
                );

        return parsePlan(
                response
        );
    }

    private String buildObservationContext(
            ReActContext context
    ) {

        StringBuilder sb =
                new StringBuilder();

        for (ToolObservation observation
                : context.getObservations()) {

            sb.append(
                    observation.getToolName()
            );

            sb.append(": ");

            sb.append(
                    observation.getResult()
            );

            sb.append("\n");
        }

        return sb.toString();
    }

    private String buildErrorContext(
            ReActContext context
    ) {

        StringBuilder sb =
                new StringBuilder();

        for (AgentError error
                : context.getErrors()) {

            sb.append(
                    error.getToolName()
            );

            sb.append(": ");

            sb.append(
                    error.getErrorMessage()
            );

            sb.append("\n");
        }

        return sb.toString();
    }

    private AgentPlan parsePlan(
            String response
    ) {

        String thought = "";

        List<String> actions =
                new ArrayList<>();

        boolean actionSection = false;

        String[] lines =
                response.split("\n");

        for (String line : lines) {

            line = line.trim();

            if (line.startsWith(
                    "THOUGHT="
            )) {

                thought =
                        line.replace(
                                "THOUGHT=",
                                ""
                        ).trim();
            }

            else if (line.startsWith(
                    "ACTIONS="
            )) {

                actionSection = true;
            }

            else if (
                    actionSection
                            &&
                            !line.isBlank()
            ) {

                actions.add(
                        line
                );
            }
        }

        return new AgentPlan(
                thought,
                actions
        );
    }
}