package com.example.demo.DTO.Match;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchRequestDTO {
    private Long userId1;
    private Long userId2;
}
