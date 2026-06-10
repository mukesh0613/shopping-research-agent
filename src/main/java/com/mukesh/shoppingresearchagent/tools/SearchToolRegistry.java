package com.mukesh.shoppingresearchagent.tools;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchToolRegistry {

    private final List<SearchTool> tools;

    public SearchToolRegistry(
            List<SearchTool> tools
    ) {

        this.tools = tools;
    }

    public List<SearchTool> getTools() {

        return tools;
    }
}