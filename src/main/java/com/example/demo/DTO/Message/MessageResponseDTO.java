package com.example.demo.DTO.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponseDTO {
    private Long id;
    private Long matchId;
    private Long senderId;
    private String content;
    private Boolean isRead;
    private LocalDateTime timestamp;
}
