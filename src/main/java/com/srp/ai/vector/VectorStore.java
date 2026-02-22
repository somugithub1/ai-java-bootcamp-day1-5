package com.srp.ai.vector;

public interface VectorStore {
    void index(String id, String content, List<Double> embedding);
    List<String> search(List<Double> embedding, int topK);
}