package com.mukesh.shoppingresearchagent.dto.groq.response;

import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroqResponse {
    private List<Choice> choices;
}
