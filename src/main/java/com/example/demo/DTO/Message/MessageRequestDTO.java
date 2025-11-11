package com.example.demo.DTO.Message;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDTO {
    private Long matchId;
    private Long senderId;
    private String content;
}
