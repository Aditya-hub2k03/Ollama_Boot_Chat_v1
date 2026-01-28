package com.yp.ollama.dto;

import lombok.Data;

@Data
public class ChatResponse {
    private String response;

    // Method to format the response as Markdown
    public String getFormattedResponse() {
        return response
                .replace("\\n", "\n") // Replace escaped newlines with actual newlines
                .replace("\\\"", "\"") // Replace escaped quotes with actual quotes
                .replace("\\\\", "\\"); // Replace escaped backslashes with actual backslashes
    }
}
