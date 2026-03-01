package com.langchain4j_poc.service.impl;

import com.langchain4j_poc.service.GeminiAssistant;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final GeminiAssistant geminiAssistant;

    public ChatService(GeminiAssistant geminiAssistant) {
        this.geminiAssistant = geminiAssistant;
    }

    public String chat(String message) {
        return geminiAssistant.chat(message);
    }
}
