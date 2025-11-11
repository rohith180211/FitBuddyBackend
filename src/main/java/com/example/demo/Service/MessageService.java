package com.example.demo.Service;

import com.example.demo.DTO.Message.MessageRequestDTO;
import com.example.demo.DTO.Message.MessageResponseDTO;

import java.util.List;

public interface MessageService {
    MessageResponseDTO sendMessage(MessageRequestDTO request);
    List<MessageResponseDTO> getMessagesForMatch(Long matchId);
    void markMessageAsRead(Long messageId);
}
