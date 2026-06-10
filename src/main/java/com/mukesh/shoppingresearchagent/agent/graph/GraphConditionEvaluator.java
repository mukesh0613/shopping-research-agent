package com.mukesh.shoppingresearchagent.agent.graph;

import com.mukesh.shoppingresearchagent.agent.react.ReActContext;
import org.springframework.stereotype.Component;

@Component
public class GraphConditionEvaluator {

    public boolean evaluate(

            GraphStateCondition condition,

            ReActContext context

    ) {

        if (condition == null) {

            return true;
        }

        Object value =
                context.getMemory()
                        .get(
                                condition.getKey()
                        );

        if (value == null) {

            return false;
        }

        return value.toString()
                .equalsIgnoreCase(
                        condition.getExpectedValue()
                );
    }
}