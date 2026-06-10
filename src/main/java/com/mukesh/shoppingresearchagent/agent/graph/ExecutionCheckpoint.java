package com.mukesh.shoppingresearchagent.agent.graph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutionCheckpoint {

    private String currentNode;

    private Map<String, Object> memory =
            new HashMap<>();

    private LocalDateTime timestamp;
}