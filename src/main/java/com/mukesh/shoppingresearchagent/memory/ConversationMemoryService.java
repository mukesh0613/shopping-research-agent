package com.mukesh.shoppingresearchagent.memory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConversationMemoryService {

    private static final int MAX_MESSAGES = 10;

    private final ConversationMemoryStore memoryStore;

    public void addUserMessage(
            String message
    ) {

        memoryStore
                .getMemory()
                .getMessages()
                .add(
                        new ConversationMessage(
                                "USER",
                                message
                        )
                );

        trimMemory();
    }

    public void addAssistantMessage(
            String message
    ) {

        memoryStore
                .getMemory()
                .getMessages()
                .add(
                        new ConversationMessage(
                                "ASSISTANT",
                                message
                        )
                );

        trimMemory();
    }

    public ConversationMemory getMemory() {

        return memoryStore.getMemory();
    }

    public void clear() {

        memoryStore.clear();
    }

    private void trimMemory() {

        List<ConversationMessage> messages =
                memoryStore
                        .getMemory()
                        .getMessages();

        while (
                messages.size()
                        > MAX_MESSAGES
        ) {

            messages.remove(0);
        }
    }
}