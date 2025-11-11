package com.example.demo.DTO.Match;

import com.example.demo.Entity.MatchStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MatchResponseDTO {
    private Long id;
    private Long user1Id;
    private Long user2Id;
    private MatchStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
