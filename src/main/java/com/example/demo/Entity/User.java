package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String email;


    @NotBlank
    private String password;

    private String gender;
    private Integer age;

    private String fitnessGoal;
    private String workoutTypes;
    private String availability;

    private Double latitude;
    private Double longitude;

    @Column(length = 500)
    private String bio;

    private String profilePictureUrl;
    private Integer xpPoints = 0;
    private Integer streakDays = 0;

    private LocalDate lastActiveDate;

    @Column(updatable = false)
    private LocalDate createdAt = LocalDate.now();

    private LocalDate updatedAt = LocalDate.now();
}
