# AI Backend Platform — Copilot Instructions

Purpose: make AI coding agents productive quickly in this Spring Boot + Ollama + RAG codebase.

## Quick architecture
- Controller → Service → PromptBuilder/RagService → EmbeddingService → VectorStore → LlmProvider → OllamaProvider
- Key packages: config/, controller/, service/, llm/, vector/, model/, exception/, util/

## Key files to inspect
- src/main/java/com/example/ai/controller/ChatController.java
- src/main/java/com/example/ai/service/{ChatService.java,RagService.java,EmbeddingService.java,PromptBuilder.java}
- src/main/java/com/example/ai/llm/{LlmProvider.java,OllamaProvider.java}
- src/main/java/com/example/ai/vector/{VectorStore.java,InMemoryVectorStore.java}
- src/main/java/com/example/ai/config/WebClientConfig.java
- src/main/resources/application.yml (contains Ollama endpoint & model settings)
- src/test/java/... (ChatServiceTest, RagServiceTest, InMemoryVectorStoreTest)

## Setup & run (explicit)
- Pull/run local model:
  - ollama pull llama3
  - ollama run llama3
  - Ollama default endpoint: http://localhost:11434
- Build & run app:
  - mvn clean install
  - mvn spring-boot:run
- Run tests:
  - mvn test

## Project-specific patterns
- LLM strategy: implement LlmProvider for new providers (no controller changes).
- Vector store is pluggable: implement VectorStore for OpenSearch/Pinecone.
- PromptBuilder is deterministic — edit only when changing injection rules.
- Micrometer timing: LLM calls should use timer `llm.call.latency` with tags (provider, model).

## Examples of actionable edits
- Add a provider:
  - create new class in llm/ implementing LlmProvider
  - instrument calls with meterRegistry.timer("llm.call.latency", "provider", "<name>").record(...)
  - register via Spring config
- Swap vector store:
  - implement VectorStore interface in vector/
  - update bean configuration (if present) or component scanning

## Partially developed / attention areas
- OpenSearch VectorStore indicated as “future” — expect missing or TODO code in vector/ for persistence.
- Some service methods may be incomplete; prefer small, well-tested changes and add TODO comments rather than large refactors.

## Debugging scenarios to handle
- Ollama unreachable:
  - Check application.yml for endpoint
  - Confirm `ollama run` is active and model pulled
  - Curl http://localhost:11434/ to validate
- Model not pulled / wrong model:
  - Run `ollama pull llama3` and `ollama run llama3`
- Slow/no responses:
  - Increase HTTP/WebClient timeouts in config/WebClientConfig.java
  - Use metrics (llm.call.latency) to locate slow ops
- Embedding mismatches:
  - Inspect util/CosineSimilarity.java and embedding vector dimensions
  - Compare embedding outputs in tests under src/test/
- Partially implemented code:
  - Search for TODO or methods throwing UnsupportedOperationException
  - Run unit tests to find failing boundaries before modifying orchestration

## How to be conservative as an agent
- Run mvn test locally before proposing wide changes.
- When code is partial: add clear TODO comments, unit tests covering your change, and small, focused commits/PRs.
- Prefer adding new implementations behind interfaces (LlmProvider/VectorStore) rather than changing callers.

## Useful repo search queries
- Find TODOs / incomplete code:
  - Windows PowerShell: Get-ChildItem -Recurse -Include *.java | Select-String -Pattern "TODO|throw new UnsupportedOperationException"
- Locate Ollama config:
  - Search for "ollama" in src/main/resources/application*.yml

---

Please review: any additional debug scenarios or partially-implemented files you want highlighted?// filepath: c:\Soumya\aiTraining\ai-java-bootcamp-day1-5\.github\copilot-instructions.md
# AI Backend Platform — Copilot Instructions

Purpose: make AI coding agents productive quickly in this Spring Boot + Ollama + RAG codebase.

## Quick architecture
- Controller → Service → PromptBuilder/RagService → EmbeddingService → VectorStore → LlmProvider → OllamaProvider
- Key packages: config/, controller/, service/, llm/, vector/, model/, exception/, util/

## Key files to inspect
- src/main/java/com/example/ai/controller/ChatController.java
- src/main/java/com/example/ai/service/{ChatService.java,RagService.java,EmbeddingService.java,PromptBuilder.java}
- src/main/java/com/example/ai/llm/{LlmProvider.java,OllamaProvider.java}
- src/main/java/com/example/ai/vector/{VectorStore.java,InMemoryVectorStore.java}
- src/main/java/com/example/ai/config/WebClientConfig.java
- src/main/resources/application.yml (contains Ollama endpoint & model settings)
- src/test/java/... (ChatServiceTest, RagServiceTest, InMemoryVectorStoreTest)

## Setup & run (explicit)
- Pull/run local model:
  - ollama pull llama3
  - ollama run llama3
  - Ollama default endpoint: http://localhost:11434
- Build & run app:
  - mvn clean install
  - mvn spring-boot:run
- Run tests:
  - mvn test

## Project-specific patterns
- LLM strategy: implement LlmProvider for new providers (no controller changes).
- Vector store is pluggable: implement VectorStore for OpenSearch/Pinecone.
- PromptBuilder is deterministic — edit only when changing injection rules.
- Micrometer timing: LLM calls should use timer `llm.call.latency` with tags (provider, model).

## Examples of actionable edits
- Add a provider:
  - create new class in llm/ implementing LlmProvider
  - instrument calls with meterRegistry.timer("llm.call.latency", "provider", "<name>").record(...)
  - register via Spring config
- Swap vector store:
  - implement VectorStore interface in vector/
  - update bean configuration (if present) or component scanning

## Partially developed / attention areas
- OpenSearch VectorStore indicated as “future” — expect missing or TODO code in vector/ for persistence.
- Some service methods may be incomplete; prefer small, well-tested changes and add TODO comments rather than large refactors.

## Debugging scenarios to handle
- Ollama unreachable:
  - Check application.yml for endpoint
  - Confirm `ollama run` is active and model pulled
  - Curl http://localhost:11434/ to validate
- Model not pulled / wrong model:
  - Run `ollama pull llama3` and `ollama run llama3`
- Slow/no responses:
  - Increase HTTP/WebClient timeouts in config/WebClientConfig.java
  - Use metrics (llm.call.latency) to locate slow ops
- Embedding mismatches:
  - Inspect util/CosineSimilarity.java and embedding vector dimensions
  - Compare embedding outputs in tests under src/test/
- Partially implemented code:
  - Search for TODO or methods throwing UnsupportedOperationException
  - Run unit tests to find failing boundaries before modifying orchestration

## How to be conservative as an agent
- Run mvn test locally before proposing wide changes.
- When code is partial: add clear TODO comments, unit tests covering your change, and small, focused commits/PRs.
- Prefer adding new implementations behind interfaces (LlmProvider/VectorStore) rather than changing callers.

## Useful repo search queries
- Find TODOs / incomplete code:
  - Windows PowerShell: Get-ChildItem -Recurse -Include *.java | Select-String -Pattern "TODO|throw new UnsupportedOperationException"
- Locate Ollama config:
  - Search for "ollama" in src/main/resources/application*.yml

---

Please review: any additional debug scenarios or partially-implemented files you want highlighted?