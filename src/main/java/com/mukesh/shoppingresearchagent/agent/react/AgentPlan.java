package com.mukesh.shoppingresearchagent.agent.react;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentPlan {

    private String thought;

    private List<String> actions;
}
