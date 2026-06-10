package com.mukesh.shoppingresearchagent.memory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemoryPromptBuilder {

    private final ConversationMemoryService
            memoryService;

    public String buildMemoryContext() {

        StringBuilder builder =
                new StringBuilder();

        ConversationMemory memory =
                memoryService.getMemory();

        if (memory.getMessages().isEmpty()) {

            return "";
        }

        builder.append(
                "Previous Conversation:\n\n"
        );

        for (ConversationMessage message
                : memory.getMessages()) {

            builder.append(
                    message.getRole()
            );

            builder.append(
                    ":\n"
            );

            builder.append(
                    message.getContent()
            );

            builder.append(
                    "\n\n"
            );
        }

        return builder.toString();
    }
}