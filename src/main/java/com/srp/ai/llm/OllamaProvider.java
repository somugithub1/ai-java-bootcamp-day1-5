package com.srp.ai.llm;

@Component
public class OllamaProvider implements LlmProvider {

    private final WebClient webClient;

    @Value("${ollama.model}")
    private String model;

    public OllamaProvider(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<String> generate(String prompt) {

        Map<String, Object> request = Map.of(
                "model", model,
                "prompt", prompt,
                "stream", false
        );

        return webClient.post()
                .uri("/api/generate")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> json.get("response").asText());
    }
}