package com.srp.ai.vector;

@Component
public class InMemoryVectorStore implements VectorStore {

    private final Map<String, List<Double>> store = new HashMap<>();
    private final Map<String, String> documents = new HashMap<>();

    @Override
    public void index(String id, String content, List<Double> embedding) {
        store.put(id, embedding);
        documents.put(id, content);
    }

    @Override
    public List<String> search(List<Double> queryEmbedding, int topK) {
        return store.entrySet().stream()
                .sorted((e1, e2) ->
                        Double.compare(
                                cosineSimilarity(queryEmbedding, e2.getValue()),
                                cosineSimilarity(queryEmbedding, e1.getValue())))
                .limit(topK)
                .map(e -> documents.get(e.getKey()))
                .toList();
    }

    private double cosineSimilarity(List<Double> v1, List<Double> v2) {
        double dot = 0, normA = 0, normB = 0;
        for (int i = 0; i < v1.size(); i++) {
            dot += v1.get(i) * v2.get(i);
            normA += Math.pow(v1.get(i), 2);
            normB += Math.pow(v2.get(i), 2);
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}