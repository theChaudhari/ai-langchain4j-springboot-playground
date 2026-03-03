package com.langchain4j_poc.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;


@AiService
public interface TaskAgent {

    @SystemMessage("""
        You are a task management AI assistant.
        Use available tools to manage tasks.
        Always call tools instead of guessing.
    """)
    String chat(@MemoryId String memoryId, @UserMessage String message);;
}
