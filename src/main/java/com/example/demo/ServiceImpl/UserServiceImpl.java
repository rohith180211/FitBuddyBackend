package com.example.demo.ServiceImpl;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 🟢 CREATE user
    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setGender(request.getGender());
        user.setAge(request.getAge());
        user.setFitnessGoal(request.getFitnessGoal());
        user.setWorkoutTypes(request.getWorkoutTypes());
        user.setAvailability(request.getAvailability());
        user.setBio(request.getBio());
        user.setProfilePictureUrl(request.getProfilePictureUrl());
        user.setLatitude(request.getLatitude());
        user.setLongitude(request.getLongitude());
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());

        User savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    // 🟢 GET all users
    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // 🟢 GET user by ID
    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID " + id));
        return mapToResponse(user);
    }

    // 🟢 UPDATE user
    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID " + id));

        if (request.getName() != null) existingUser.setName(request.getName());
        if (request.getEmail() != null) existingUser.setEmail(request.getEmail());
        if (request.getPassword() != null) existingUser.setPassword(request.getPassword());
        if (request.getGender() != null) existingUser.setGender(request.getGender());
        if (request.getAge() != null) existingUser.setAge(request.getAge());
        if (request.getFitnessGoal() != null) existingUser.setFitnessGoal(request.getFitnessGoal());
        if (request.getWorkoutTypes() != null) existingUser.setWorkoutTypes(request.getWorkoutTypes());
        if (request.getAvailability() != null) existingUser.setAvailability(request.getAvailability());
        if (request.getBio() != null) existingUser.setBio(request.getBio());
        if (request.getProfilePictureUrl() != null) existingUser.setProfilePictureUrl(request.getProfilePictureUrl());
        if (request.getLatitude() != null) existingUser.setLatitude(request.getLatitude());
        if (request.getLongitude() != null) existingUser.setLongitude(request.getLongitude());

        existingUser.setUpdatedAt(LocalDate.now());
        User updated = userRepository.save(existingUser);
        return mapToResponse(updated);
    }

    // 🟢 DELETE user
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // 🧩 MAPPER: Entity → Response DTO
    private UserResponseDTO mapToResponse(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .gender(user.getGender())
                .age(user.getAge())
                .fitnessGoal(user.getFitnessGoal())
                .workoutTypes(user.getWorkoutTypes())
                .availability(user.getAvailability())
                .bio(user.getBio())
                .profilePictureUrl(user.getProfilePictureUrl())
                .xpPoints(user.getXpPoints())
                .streakDays(user.getStreakDays())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
