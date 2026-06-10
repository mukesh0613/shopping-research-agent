package com.mukesh.shoppingresearchagent.agent.graph;

import com.mukesh.shoppingresearchagent.agent.react.ReActContext;
import org.springframework.stereotype.Component;

@Component
public class StartNode implements AgentNode {

    @Override
    public String getNodeName() {

        return "START";
    }

    @Override
    public void execute(
            ReActContext context
    ) {

        System.out.println(
                "Workflow Started"
        );
    }
}