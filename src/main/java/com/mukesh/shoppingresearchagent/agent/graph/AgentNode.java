package com.mukesh.shoppingresearchagent.agent.graph;

import com.mukesh.shoppingresearchagent.agent.react.ReActContext;

public interface AgentNode {
    String getNodeName();
    void execute(ReActContext context);
}
