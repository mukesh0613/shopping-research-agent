package com.mukesh.shoppingresearchagent.agent;

import org.springframework.stereotype.Component;
import java.util.*;


@Component
public class ToolRegistry {
    private final Map<String, ResearchTool> tools =
            new HashMap<>();

    public ToolRegistry(
            List<ResearchTool> toolList
    ) {

        for (ResearchTool tool : toolList) {

            tools.put(
                    tool.getToolName(),
                    tool
            );
        }
    }

    public ResearchTool getTool(
            String toolName
    ) {

        return tools.get(toolName);
    }
}
