package com.example.demo.Service;

import com.example.demo.DTO.Match.MatchRequestDTO;
import com.example.demo.DTO.Match.MatchResponseDTO;

import java.util.List;

public interface MatchService {
    MatchResponseDTO createLike(MatchRequestDTO request);
    MatchResponseDTO rejectUser(MatchRequestDTO request);
    List<MatchResponseDTO> getMatchesForUser(Long userId);
}
