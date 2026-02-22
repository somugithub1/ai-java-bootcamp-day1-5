package com.srp.ai.llm;

public interface LlmProvider {
    Mono<String> generate(String prompt);
}