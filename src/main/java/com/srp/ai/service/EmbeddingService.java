package com.srp.ai.service;

@Service
public class EmbeddingService {

    private final WebClient webClient;

    @Value("${ollama.model}")
    private String model;

    public EmbeddingService(WebClient ollamaWebClient) {
        this.webClient = ollamaWebClient;
    }

    public Mono<List<Double>> generateEmbedding(String text) {

        Map<String, Object> request = Map.of(
                "model", model,
                "prompt", text
        );

        return webClient.post()
                .uri("/api/embeddings")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    List<Double> vector = new ArrayList<>();
                    json.get("embedding").forEach(v -> vector.add(v.asDouble()));
                    return vector;
                });
    }
}