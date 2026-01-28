package com.yp.ollama.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

@Service
public class OllamaService {
    @Value("${ollama.api.url}")
    private String ollamaApiUrl;

    @Value("${ollama.api.model}")
    private String ollamaModel;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public OllamaService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String getOllamaResponse(String userMessage) {
        String requestBody = String.format(
                "{\"model\":\"%s\",\"prompt\":\"%s\",\"stream\":false}",
                ollamaModel,
                userMessage.replace("\"", "\\\"")
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(ollamaApiUrl, request, String.class);
            String responseBody = response.getBody();

            // Parse the JSON response and extract the "response" field
            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode responseNode = rootNode.path("response");

            return responseNode.asText();
        } catch (Exception e) {
            return "Error calling Ollama API: " + e.getMessage();
        }
    }
}
