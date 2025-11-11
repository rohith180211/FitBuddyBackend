package com.example.demo.Service;

import com.example.demo.DTO.User.UserRequestDTO;
import com.example.demo.DTO.User.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO request);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(Long id, UserRequestDTO request);
    void deleteUser(Long id);
}
