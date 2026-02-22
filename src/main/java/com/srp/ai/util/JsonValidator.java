package com.srp.ai.util;
@Component
public class JsonValidator {

    private final ObjectMapper mapper = new ObjectMapper();

    public StructuredResponse validate(String json) {
        try {
            return mapper.readValue(json, StructuredResponse.class);
        } catch (Exception e) {
            return new StructuredResponse("INVALID_RESPONSE", "low");
        }
    }
}