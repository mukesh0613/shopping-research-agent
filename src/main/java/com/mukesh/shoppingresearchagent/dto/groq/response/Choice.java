package com.mukesh.shoppingresearchagent.dto.groq.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Choice {
    private ResponseMessage message;
}
