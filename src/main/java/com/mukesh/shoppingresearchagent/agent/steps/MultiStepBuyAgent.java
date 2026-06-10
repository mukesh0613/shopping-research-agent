package com.mukesh.shoppingresearchagent.agent.steps;

import com.mukesh.shoppingresearchagent.agent.AgentContext;
import com.mukesh.shoppingresearchagent.agent.AgentStep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MultiStepBuyAgent {
  /*  private final ProductExtractionStep extractionStep;
    private final AnalyticsStep analyticsStep;
    private final ExplanationStep explanationStep;
*/
    private final List<AgentStep> agentStepList;
    public String execute(String query) {

        AgentContext context = new AgentContext();

        context.setQuery(query);

       /* extractionStep.execute(context);

        analyticsStep.execute(context);

        explanationStep.execute(context);*/

        for(AgentStep step : agentStepList){

            step.execute(context);
        }
        return context.getReport();
    }
}
