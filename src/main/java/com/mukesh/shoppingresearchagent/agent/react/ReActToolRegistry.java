package com.mukesh.shoppingresearchagent.agent.react;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ReActToolRegistry {
    private final Map<String, AgentTool> tools;

    public ReActToolRegistry(List<AgentTool> toolList){
        this.tools=toolList.stream()
                .collect(Collectors.toMap(AgentTool::getToolName,
                        tool -> tool));
    }
    public AgentTool getTool(String toolName) {
        return tools.get(toolName);
    }
}
