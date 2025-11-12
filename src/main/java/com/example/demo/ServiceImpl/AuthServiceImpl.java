package com.example.demo.ServiceImpl;

import com.example.demo.DTO.Auth.AuthResponse;
import com.example.demo.DTO.Auth.LoginRequest;
import com.example.demo.DTO.Auth.RegisterRequest;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.AuthService;
import com.example.demo.Util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered!");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .gender(request.getGender())
                .age(request.getAge())
                .fitnessGoal(request.getFitnessGoal())
                .workoutTypes(request.getWorkoutTypes())
                .availability(request.getAvailability())
                .bio(request.getBio())
                .profilePictureUrl(request.getProfilePictureUrl())
                .build();

        userRepository.save(user);
        String token = jwtTokenProvider.generateToken(user.getEmail());

        return new AuthResponse(token, user.getId(), user.getEmail(), user.getName());
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail());
        return new AuthResponse(token, user.getId(), user.getEmail(), user.getName());
    }
}
