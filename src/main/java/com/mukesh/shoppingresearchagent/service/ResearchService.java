package com.mukesh.shoppingresearchagent.service;

import com.mukesh.shoppingresearchagent.agent.ResearchTool;
import com.mukesh.shoppingresearchagent.agent.ToolRegistry;
import com.mukesh.shoppingresearchagent.dto.ResearchResponseDto;
import com.mukesh.shoppingresearchagent.enums.ResearchIntent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResearchService {

    private final IntentRouterService intentRouterService;

    private final ToolRegistry toolRegistry;

    public ResearchResponseDto research(
            String query
    ) {

        ResearchIntent intent =
                intentRouterService
                        .detectIntent(query);

        ResearchTool tool =
                toolRegistry
                        .getTool(
                                intent.name()
                        );

        String report =
                tool.execute(query);

        return new ResearchResponseDto(
                query,
                report
        );
    }
}