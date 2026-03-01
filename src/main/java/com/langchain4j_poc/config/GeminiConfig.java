package com.langchain4j_poc.config;

import com.langchain4j_poc.service.AiValidatorAssistant;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class GeminiConfig {
    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.model}")
    private String model;

    @Bean
    public GoogleAiGeminiChatModel geminiChatModel() {
        return GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName(model)
                .maxOutputTokens(1024)
                .temperature(0.0)
                .timeout(Duration.ofSeconds(60))
                .maxRetries(3)
                .build();
    }

    @Bean
    public AiValidatorAssistant aiValidatorAssistant(GoogleAiGeminiChatModel geminiChatModel) {
        return AiServices.builder(AiValidatorAssistant.class)
                .chatLanguageModel(geminiChatModel)
                .build();
    }
}
