package com.example.demo.DTO.User;

import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private String gender;
    private Integer age;
    private String fitnessGoal;
    private String workoutTypes;
    private String availability;
    private String bio;
    private String profilePictureUrl;
    private Double latitude;
    private Double longitude;
}
