package com.srp.ai.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ChatService {

    private final WebClient webClient;
    private final PromptBuilder promptBuilder;

    @Value("${ollama.model}")
    private String model;

    public ChatService(WebClient ollamaWebClient,
                       PromptBuilder promptBuilder) {
        this.webClient = ollamaWebClient;
        this.promptBuilder = promptBuilder;
    }

    public Mono<String> chat(String input) {

        String finalPrompt = promptBuilder.buildPrompt(input);

        Map<String, Object> requestBody = Map.of(
                "model", model,
                "prompt", finalPrompt,
                "stream", false
        );

        return webClient.post()
                .uri("/api/generate")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .timeout(Duration.ofSeconds(30))
                .map(json -> json.get("response").asText());
    }
}