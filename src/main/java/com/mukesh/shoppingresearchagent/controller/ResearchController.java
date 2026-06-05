package com.mukesh.shoppingresearchagent.controller;

import com.mukesh.shoppingresearchagent.dto.ResearchRequestDto;
import com.mukesh.shoppingresearchagent.dto.ResearchResponseDto;
import com.mukesh.shoppingresearchagent.service.ResearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/research")
@RequiredArgsConstructor
public class ResearchController {
    private final ResearchService researchService;

    @PostMapping
    public ResearchResponseDto research(@Valid @RequestBody ResearchRequestDto request){
        return researchService.research(request.getQuery());
    }
}
