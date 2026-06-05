package com.mukesh.shoppingresearchagent.dto.gemini.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseContent {
    private List<ResponsePart> parts;
}
