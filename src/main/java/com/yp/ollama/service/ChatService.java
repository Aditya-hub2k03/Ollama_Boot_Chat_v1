package com.yp.ollama.service;

import com.yp.ollama.dto.ChatRequest;
import com.yp.ollama.dto.ChatResponse;
import com.yp.ollama.entity.ChatMessage;
import com.yp.ollama.mapper.ChatMapper;
import com.yp.ollama.repository.ChatRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;
    private final OllamaService ollamaService;

    public ChatService(ChatRepository chatRepository, ChatMapper chatMapper, OllamaService ollamaService) {
        this.chatRepository = chatRepository;
        this.chatMapper = chatMapper;
        this.ollamaService = ollamaService;
    }

    public ChatResponse chat(ChatRequest chatRequest) {
        String ollamaResponse = ollamaService.getOllamaResponse(chatRequest.getMessage());
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUserMessage(chatRequest.getMessage());
        chatMessage.setBotResponse(ollamaResponse);
        chatRepository.save(chatMessage);
        return chatMapper.toChatResponse(chatMessage);
    }
}
