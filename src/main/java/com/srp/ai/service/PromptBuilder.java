package com.srp.ai.service;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

  /**
   * Builds a prompt string for the AI assistant.
   *
   * @param input the user's question
   * @return the formatted prompt string
   */
  public String buildPrompt(String input) {
    return """
                You are a precise backend AI assistant.
                Respond ONLY in valid JSON.
                If unsure, return:
                {
                  "answer": "UNKNOWN",
                  "confidence": "low"
                }

                User Question:
                """ + input;
  }
}