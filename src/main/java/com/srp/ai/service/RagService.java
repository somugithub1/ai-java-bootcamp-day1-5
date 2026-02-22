package com.srp.ai.service;

@Service
public class RagService {

    private final EmbeddingService embeddingService;
    private final VectorStore vectorStore;
    private final ChatService chatService;
    private final PromptBuilder promptBuilder;

    public RagService(EmbeddingService embeddingService,
                      VectorStore vectorStore,
                      ChatService chatService,
                      PromptBuilder promptBuilder) {
        this.embeddingService = embeddingService;
        this.vectorStore = vectorStore;
        this.chatService = chatService;
        this.promptBuilder = promptBuilder;
    }

    public Mono<String> ask(String question) {

        return embeddingService.generateEmbedding(question)
                .map(queryEmbedding -> vectorStore.search(queryEmbedding, 3))
                .flatMap(contextDocs -> {

                    String context = String.join("\n\n", contextDocs);

                    String ragPrompt = """
                            Use ONLY the context below to answer.

                            Context:
                            """ + context + """

                            Question:
                            """ + question;

                    return chatService.chat(ragPrompt);
                });
    }
}