package com.mukesh.shoppingresearchagent.memory;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConversationMemory {

    private List<ConversationMessage>
            messages =
            new ArrayList<>();
}