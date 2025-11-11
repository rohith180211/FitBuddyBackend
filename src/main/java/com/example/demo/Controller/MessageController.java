package com.example.demo.Controller;


import com.example.demo.DTO.Message.MessageRequestDTO;
import com.example.demo.DTO.Message.MessageResponseDTO;
import com.example.demo.Service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@CrossOrigin(origins = "*")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<MessageResponseDTO> sendMessage(@RequestBody MessageRequestDTO request) {
        return ResponseEntity.ok(messageService.sendMessage(request));
    }

    @GetMapping("/match/{matchId}")
    public ResponseEntity<List<MessageResponseDTO>> getMessagesByMatch(@PathVariable Long matchId) {
        return ResponseEntity.ok(messageService.getMessagesForMatch(matchId));
    }

    @PutMapping("/{messageId}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long messageId) {
        messageService.markMessageAsRead(messageId);
        return ResponseEntity.ok().build();
    }
}
