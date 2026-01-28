package com.yp.ollama.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000) // Increase length for userMessage if needed
    private String userMessage;

    @Lob // Use @Lob for large text (maps to TEXT in MySQL)
    @Column(columnDefinition = "TEXT") // Explicitly set column type to TEXT
    private String botResponse;
}
