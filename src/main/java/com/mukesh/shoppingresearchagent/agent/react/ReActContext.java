package com.mukesh.shoppingresearchagent.agent.react;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ReActContext {

    private String query;

    private List<AgentThought> thoughts =
            new ArrayList<>();

    private List<ToolObservation> observations =
            new ArrayList<>();

    private List<ExecutionRecord> executionHistory =
            new ArrayList<>();

    private List<AgentError> errors =
            new ArrayList<>();

    private Map<String,Object> memory =
            new HashMap<>();

    private String finalAnswer;
}