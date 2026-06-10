package com.mukesh.shoppingresearchagent.agent.graph;

import org.springframework.stereotype.Component;

@Component
public class CheckpointManager {

    private ExecutionCheckpoint checkpoint;

    public void save(
            ExecutionCheckpoint checkpoint
    ) {

        this.checkpoint = checkpoint;
    }

    public ExecutionCheckpoint load() {

        return checkpoint;
    }

    public boolean exists() {

        return checkpoint != null;
    }

    public void clear() {

        checkpoint = null;
    }
}