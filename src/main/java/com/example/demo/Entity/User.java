package com.example.demo.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String gender;
    private Integer age;

    private String fitnessGoal;         // e.g. "Build Muscle"
    private String workoutTypes;        // e.g. "Gym, Running"
    private String availability;        // e.g. "Weekdays 7am-9am"

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
