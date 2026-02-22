package com.srp.ai.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srp.ai.service.ChatService;
import com.srp.ai.util.JsonValidator;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;
    private final JsonValidator validator;

    public ChatController(ChatService chatService,
                          JsonValidator validator) {
        this.chatService = chatService;
        this.validator = validator;
    }

    @PostMapping
    public Mono<StructuredResponse> chat(@RequestBody ChatRequest request) {
        return chatService.chat(request.message())
                .map(validator::validate);
    }
}