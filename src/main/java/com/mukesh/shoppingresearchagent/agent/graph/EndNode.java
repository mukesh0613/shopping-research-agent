package com.mukesh.shoppingresearchagent.agent.graph;

import com.mukesh.shoppingresearchagent.agent.react.ReActContext;
import org.springframework.stereotype.Component;

@Component
public class EndNode implements AgentNode {

    @Override
    public String getNodeName() {

        return "END";
    }

    @Override
    public void execute(
            ReActContext context
    ) {

        System.out.println(
                "Workflow Completed"
        );
    }
}