package com.langchain4j_poc.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface GeminiAssistant {
    @SystemMessage("You are a helpful and friendly AI assistant.")
    String chat(@UserMessage String userMessage);
}
