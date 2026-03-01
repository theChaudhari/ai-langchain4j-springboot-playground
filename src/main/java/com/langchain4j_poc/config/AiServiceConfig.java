package com.langchain4j_poc.config;

import com.langchain4j_poc.service.GeminiAssistant;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiServiceConfig {
    @Bean
    public GeminiAssistant geminiAssistant(GoogleAiGeminiChatModel chatModel) {
        return AiServices.builder(GeminiAssistant.class)
                .chatLanguageModel(chatModel)
                .build();
    }
}
