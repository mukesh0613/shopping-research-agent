package com.mukesh.shoppingresearchagent.memory;

import org.springframework.stereotype.Component;

@Component
public class ConversationMemoryStore {

    private final ConversationMemory
            memory =
            new ConversationMemory();

    public ConversationMemory getMemory() {

        return memory;
    }

    public void clear() {

        memory.getMessages().clear();
    }
}