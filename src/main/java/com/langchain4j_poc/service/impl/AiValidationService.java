package com.langchain4j_poc.service.impl;


import com.langchain4j_poc.request.ValidationResult;
import com.langchain4j_poc.service.AiValidatorAssistant;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class AiValidationService {

    private final AiValidatorAssistant validatorAssistant;
    private final ObjectMapper objectMapper;


    public ValidationResult validate(String fieldName, Object requestData) {
        try {
            String jsonData = objectMapper.writeValueAsString(requestData);
            String prompt = "Validate this " + fieldName + " request:\n" + jsonData;

            log.info("Sending to Gemini for validation: {}", prompt);

            String aiResponse = validatorAssistant.validate(prompt);

            log.info("Gemini Validation Response: {}", aiResponse);

            String cleanedResponse = aiResponse
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            return objectMapper.readValue(cleanedResponse, ValidationResult.class);

        } catch (Exception e) {
            log.error("Gemini Validation failed: {}", e.getMessage());
            return ValidationResult.builder()
                    .valid(false)
                    .errors(List.of("Validation service error: " + e.getMessage()))
                    .warnings(List.of())
                    .suggestion("Please try again")
                    .confidenceScore(0)
                    .build();
        }
    }
}