package com.srp.ai.service;

@Service
public class EvaluationService {

    public double lexicalOverlapScore(String answer, String reference) {

        Set<String> a = new HashSet<>(Arrays.asList(answer.split("\\s+")));
        Set<String> b = new HashSet<>(Arrays.asList(reference.split("\\s+")));

        a.retainAll(b);

        return (double) a.size() / reference.split("\\s+").length;
    }
}