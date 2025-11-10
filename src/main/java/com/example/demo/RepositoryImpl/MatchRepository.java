package com.example.demo.RepositoryImpl;

import com.example.demo.Entity.Match;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    // Find all matches for a user
    List<Match> findByUser1OrUser2(User user1, User user2);

    // Check if a match already exists between two users
    Optional<Match> findByUser1AndUser2(User user1, User user2);

}
