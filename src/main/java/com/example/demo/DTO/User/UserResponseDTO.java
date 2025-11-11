package com.example.demo.DTO.User;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String gender;
    private Integer age;
    private String fitnessGoal;
    private String workoutTypes;
    private String availability;
    private String bio;
    private String profilePictureUrl;
    private Integer xpPoints;
    private Integer streakDays;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
