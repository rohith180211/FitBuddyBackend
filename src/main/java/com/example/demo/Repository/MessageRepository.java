package com.example.demo.Repository;

import com.example.demo.Entity.Match;
import com.example.demo.Entity.Message;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Get all messages for a given match (chat history)
    List<Message> findByMatchOrderByTimestampAsc(Match match);

    // Fetch all messages sent by a particular user
    List<Message> findBySender(User sender);

}
