package com.example.demo.Repository;


import com.example.demo.Entity.Match;
import com.example.demo.Entity.MatchStatus;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByUser1OrUser2(User user1, User user2);

    Optional<Match> findByUser1AndUser2(User user1, User user2);

    List<Match> findByUser1AndStatus(User user1, MatchStatus status);
}
