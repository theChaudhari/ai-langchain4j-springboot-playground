package com.langchain4j_poc.controller;

import com.langchain4j_poc.service.impl.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public String chat(@RequestBody String message) {
        return chatService.chat(message);
    }

    @GetMapping
    public String chatGet(@RequestParam String message) {
        return chatService.chat(message);
    }
}
