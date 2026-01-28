package com.yp.ollama.mapper;

import com.yp.ollama.dto.ChatResponse;
import com.yp.ollama.entity.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    @Mapping(target = "response", source = "botResponse")
    ChatResponse toChatResponse(ChatMessage chatMessage);
}
