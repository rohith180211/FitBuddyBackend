package com.example.demo.ServiceImpl;

import com.example.demo.DTO.Message.MessageRequestDTO;
import com.example.demo.DTO.Message.MessageResponseDTO;
import com.example.demo.Entity.Match;
import com.example.demo.Entity.Message;
import com.example.demo.Entity.User;
import com.example.demo.Repository.MatchRepository;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.MessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MessageServiceImpl implements MessageService {


    private final MessageRepository messageRepository;
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, MatchRepository matchRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MessageResponseDTO sendMessage(MessageRequestDTO request) {
        Match match = matchRepository.findById(request.getMatchId())
                .orElseThrow(() -> new RuntimeException("Match not found"));
        User sender = userRepository.findById(request.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        Message message = Message.builder()
                .match(match)
                .sender(sender)
                .content(request.getContent())
                .isRead(false)
                .timestamp(LocalDateTime.now())
                .build();

        return mapToResponse(messageRepository.save(message));
    }


    @Override
    public List<MessageResponseDTO> getMessagesForMatch(Long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Match not found"));

        return messageRepository.findByMatchOrderByTimestampAsc(match)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void markMessageAsRead(Long messageId) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        message.setIsRead(true);
        messageRepository.save(message);
    }

    private MessageResponseDTO mapToResponse(Message message) {
        return MessageResponseDTO.builder()
                .id(message.getId())
                .matchId(message.getMatch().getId())
                .senderId(message.getSender().getId())
                .content(message.getContent())
                .isRead(message.getIsRead())
                .timestamp(message.getTimestamp())
                .build();
    }
}
