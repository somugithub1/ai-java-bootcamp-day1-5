# AI Backend Platform (Spring Boot + Ollama + RAG)

A production-ready AI backend built with Spring Boot that supports:

-   Local LLM inference via Ollama
-   Embeddings generation
-   Vector search abstraction
-   Retrieval-Augmented Generation (RAG)
-   Evaluation layer
-   Observability hooks
-   Clean LLM provider strategy pattern

------------------------------------------------------------------------

## 🚀 Architecture Overview

``` mermaid
flowchart TD
    A[Client] --> B[ChatController]
    B --> C[ChatService]
    C --> D[PromptBuilder]
    C --> E[RagService]
    E --> F[EmbeddingService]
    F --> G[VectorStore]
    E --> H[LlmProvider]
    H --> I[OllamaProvider]
    G --> E
```

### Component Responsibilities

-   **Controller Layer**: Exposes REST APIs.
-   **Service Layer**: Handles business logic and orchestration.
-   **PromptBuilder**: Constructs deterministic prompts.
-   **EmbeddingService**: Generates vector embeddings.
-   **VectorStore**: Handles semantic search and indexing.
-   **LlmProvider**: Strategy abstraction for LLM integrations.
-   **OllamaProvider**: Concrete local LLM implementation.

------------------------------------------------------------------------

## 🧠 Key Features

### 1️⃣ Local LLM Integration

Runs fully locally using Ollama (no API cost, no external dependency).

### 2️⃣ Embeddings Support

Enables semantic search and RAG pipelines.

### 3️⃣ Vector Store Abstraction

Swappable implementation (InMemory → OpenSearch-ready).

### 4️⃣ RAG Pipeline

-   Embed user query
-   Retrieve relevant documents
-   Inject context
-   Generate grounded answer

### 5️⃣ Observability

Micrometer-based latency tracking for LLM calls.

------------------------------------------------------------------------

## 📦 Project Structure

    ai-backend-platform
    ├── config
    ├── controller
    ├── service
    ├── llm
    ├── vector
    ├── model
    ├── exception
    └── util

ai-backend-platform
├── pom.xml
├── README.md
├── docker-compose.yml              # future: OpenSearch, monitoring
├── .env                            # local environment variables
└── src
    ├── main
    │   ├── java
    │   │   └── com/example/ai
    │   │       ├── AiApplication.java
    │   │       │
    │   │       ├── config
    │   │       │   ├── WebClientConfig.java
    │   │       │   ├── MetricsConfig.java
    │   │       │   └── AppProperties.java
    │   │       │
    │   │       ├── controller
    │   │       │   ├── ChatController.java
    │   │       │   ├── DocumentController.java
    │   │       │   └── HealthController.java
    │   │       │
    │   │       ├── service
    │   │       │   ├── ChatService.java
    │   │       │   ├── RagService.java
    │   │       │   ├── EmbeddingService.java
    │   │       │   ├── EvaluationService.java
    │   │       │   └── PromptBuilder.java
    │   │       │
    │   │       ├── llm
    │   │       │   ├── LlmProvider.java
    │   │       │   └── OllamaProvider.java
    │   │       │
    │   │       ├── vector
    │   │       │   ├── VectorStore.java
    │   │       │   ├── InMemoryVectorStore.java
    │   │       │   └── (future) OpenSearchVectorStore.java
    │   │       │
    │   │       ├── model
    │   │       │   ├── ChatRequest.java
    │   │       │   ├── StructuredResponse.java
    │   │       │   ├── EmbeddingResponse.java
    │   │       │   └── DocumentRequest.java
    │   │       │
    │   │       ├── exception
    │   │       │   ├── GlobalExceptionHandler.java
    │   │       │   └── LlmException.java
    │   │       │
    │   │       └── util
    │   │           ├── JsonValidator.java
    │   │           └── CosineSimilarity.java
    │   │
    │   └── resources
    │       ├── application.yml
    │       ├── application-dev.yml
    │       └── application-prod.yml
    │
    └── test
        └── java
            └── com/example/ai
                ├── service
                │   ├── ChatServiceTest.java
                │   └── RagServiceTest.java
                └── vector
                    └── InMemoryVectorStoreTest.java
------------------------------------------------------------------------

## 🔧 Setup Instructions

### Install Ollama

https://ollama.com

``` bash
ollama pull llama3
ollama run llama3
```

Default local endpoint:

http://localhost:11434

------------------------------------------------------------------------

## ▶ Run Application

``` bash
mvn clean install
mvn spring-boot:run
```

App runs at:

http://localhost:8080

------------------------------------------------------------------------

## 🎯 What This Project Demonstrates

-   Enterprise-ready AI backend architecture
-   Clean separation of concerns
-   Strategy-based LLM provider design
-   RAG implementation in Java
-   Production-oriented engineering thinking

------------------------------------------------------------------------

## 📄 License

MIT License
