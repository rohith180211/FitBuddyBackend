package com.example.demo.DTO.Auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String gender;
    private Integer age;
    private String fitnessGoal;
    private String workoutTypes;
    private String availability;
    private String bio;
    private String profilePictureUrl;
}
