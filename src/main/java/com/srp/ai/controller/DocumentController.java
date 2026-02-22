package com.srp.ai.controller;

@RestController
@RequestMapping("/api/docs")
public class DocumentController {

    private final EmbeddingService embeddingService;
    private final VectorStore vectorStore;

    public DocumentController(EmbeddingService embeddingService,
                              VectorStore vectorStore) {
        this.embeddingService = embeddingService;
        this.vectorStore = vectorStore;
    }

    @PostMapping
    public Mono<String> index(@RequestBody String content) {

        String id = UUID.randomUUID().toString();

        return embeddingService.generateEmbedding(content)
                .doOnNext(embedding ->
                        vectorStore.index(id, content, embedding))
                .thenReturn("Indexed with id: " + id);
    }
}
