package com.example.demo.RepositoryImpl;


import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositoryImpl extends UserRepository {
    // Find user by email (for login)
    Optional<User> findByEmail(String email);

    // Check if a user exists by email (for registration)
    boolean existsByEmail(String email);

    // Find nearby users (basic, later we’ll add distance filtering)
    List<User> findByLatitudeBetweenAndLongitudeBetween(
            double minLat, double maxLat,
            double minLng, double maxLng
    );
}
