package com.example.demo.ServiceImpl;

import com.example.demo.DTO.Match.MatchRequestDTO;
import com.example.demo.DTO.Match.MatchResponseDTO;
import com.example.demo.Entity.Match;
import com.example.demo.Entity.MatchStatus;
import com.example.demo.Entity.User;
import com.example.demo.Repository.MatchRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.MatchService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MatchServiceImpl implements MatchService {


    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    public MatchServiceImpl(MatchRepository matchRepository, UserRepository userRepository) {
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MatchResponseDTO createLike(MatchRequestDTO request) {
        User user1 = userRepository.findById(request.getUserId1())
                .orElseThrow(() -> new RuntimeException("User1 not found"));
        User user2 = userRepository.findById(request.getUserId2())
                .orElseThrow(() -> new RuntimeException("User2 not found"));
        var reverseMatch = matchRepository.findByUser1AndUser2(user2, user1);
        if (reverseMatch.isPresent() && reverseMatch.get().getStatus() == MatchStatus.PENDING) {
            Match match = reverseMatch.get();
            match.setStatus(MatchStatus.MATCHED);
            match.setUpdatedAt(LocalDateTime.now());
            matchRepository.save(match);
            return mapToResponse(match);
        }
        Match match = Match.builder()
                .user1(user1)
                .user2(user2)
                .status(MatchStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return mapToResponse(matchRepository.save(match));
    }

    @Override
    public MatchResponseDTO rejectUser(MatchRequestDTO request) {
        User user1 = userRepository.findById(request.getUserId1())
                .orElseThrow(() -> new RuntimeException("User1 not found"));
        User user2 = userRepository.findById(request.getUserId2())
                .orElseThrow(() -> new RuntimeException("User2 not found"));

        Match match = Match.builder()
                .user1(user1)
                .user2(user2)
                .status(MatchStatus.REJECTED)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return mapToResponse(matchRepository.save(match));
    }

    @Override
    public List<MatchResponseDTO> getMatchesForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return matchRepository.findByUser1OrUser2(user, user)
                .stream()
                .filter(m -> m.getStatus() == MatchStatus.MATCHED)
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private MatchResponseDTO mapToResponse(Match match) {
        return MatchResponseDTO.builder()
                .id(match.getId())
                .user1Id(match.getUser1().getId())
                .user2Id(match.getUser2().getId())
                .status(match.getStatus())
                .createdAt(match.getCreatedAt())
                .updatedAt(match.getUpdatedAt())
                .build();
    }
}
