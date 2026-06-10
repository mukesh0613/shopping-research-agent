package com.mukesh.shoppingresearchagent.agent.react;

public interface AgentTool {
    String getToolName();
    String getOutputKey();
    ToolResult execute(ReActContext context);
    String getInputKey();

}
