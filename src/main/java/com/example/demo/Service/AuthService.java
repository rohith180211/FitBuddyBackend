package com.example.demo.Service;

import com.example.demo.DTO.Auth.AuthResponse;
import com.example.demo.DTO.Auth.LoginRequest;
import com.example.demo.DTO.Auth.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);

}
