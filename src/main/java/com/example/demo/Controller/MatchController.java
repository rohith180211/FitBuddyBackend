package com.example.demo.Controller;


import com.example.demo.DTO.Match.MatchRequestDTO;
import com.example.demo.DTO.Match.MatchResponseDTO;
import com.example.demo.Service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matches")
@CrossOrigin(origins = "*")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping("/like")
    public ResponseEntity<MatchResponseDTO> likeUser(@RequestBody MatchRequestDTO request) {
        return ResponseEntity.ok(matchService.createLike(request));
    }

    @PostMapping("/reject")
    public ResponseEntity<MatchResponseDTO> rejectUser(@RequestBody MatchRequestDTO request) {
        return ResponseEntity.ok(matchService.rejectUser(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MatchResponseDTO>> getMatchesForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(matchService.getMatchesForUser(userId));
    }
}
