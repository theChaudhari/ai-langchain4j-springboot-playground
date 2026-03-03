package com.langchain4j_poc.controller;

import com.langchain4j_poc.service.TaskAgent;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agent")
public class AgentController {

    private final TaskAgent agent;


    @PostMapping("/ask")
    public String chat(@RequestBody String message) {
        return agent.chat(message);
    }
}