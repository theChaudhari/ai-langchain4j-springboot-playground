package com.langchain4j_poc.controller;

import com.langchain4j_poc.service.TaskAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agent")
public class AgentController {

    private final TaskAgent agent;


    @PostMapping("/ask")
    public String chat(
            @RequestParam(defaultValue = "default-session") String sessionId,
            @RequestBody String message) {
        return agent.chat(sessionId, message);
    }
}