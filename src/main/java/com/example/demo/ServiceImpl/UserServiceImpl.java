package com.example.demo.ServiceImpl;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    if (updatedUser.getName() != null)
                        existingUser.setName(updatedUser.getName());
                    if (updatedUser.getEmail() != null)
                        existingUser.setEmail(updatedUser.getEmail());
                    if (updatedUser.getPassword() != null)
                        existingUser.setPassword(updatedUser.getPassword());
                    if (updatedUser.getGender() != null)
                        existingUser.setGender(updatedUser.getGender());
                    if (updatedUser.getAge() != null)
                        existingUser.setAge(updatedUser.getAge());
                    if (updatedUser.getFitnessGoal() != null)
                        existingUser.setFitnessGoal(updatedUser.getFitnessGoal());
                    if (updatedUser.getWorkoutTypes() != null)
                        existingUser.setWorkoutTypes(updatedUser.getWorkoutTypes());
                    if (updatedUser.getAvailability() != null)
                        existingUser.setAvailability(updatedUser.getAvailability());
                    if (updatedUser.getBio() != null)
                        existingUser.setBio(updatedUser.getBio());
                    if (updatedUser.getProfilePictureUrl() != null)
                        existingUser.setProfilePictureUrl(updatedUser.getProfilePictureUrl());
                    if (updatedUser.getLatitude() != null)
                        existingUser.setLatitude(updatedUser.getLatitude());
                    if (updatedUser.getLongitude() != null)
                        existingUser.setLongitude(updatedUser.getLongitude());

                    existingUser.setUpdatedAt(LocalDate.now());

                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("User not found with ID " + id));
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
