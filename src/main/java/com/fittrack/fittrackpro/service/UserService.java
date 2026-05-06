package com.fittrack.fittrackpro.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fittrack.fittrackpro.dto.ProfileUpdateForm;
import com.fittrack.fittrackpro.dto.RegistrationForm;
import com.fittrack.fittrackpro.entity.OperationType;
import com.fittrack.fittrackpro.entity.Role;
import com.fittrack.fittrackpro.entity.User;
import com.fittrack.fittrackpro.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuditLogService auditLogService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuditLogService auditLogService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.auditLogService = auditLogService;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        auditLogService.logOperation(savedUser, "REGISTRATION", "User registered with email: " + user.getEmail(), OperationType.REGISTRATION);
        return savedUser;
    }

    public User registerUser(RegistrationForm form) {
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        if (userRepository.findByEmail(form.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUsername(form.getUsername().trim());
        user.setEmail(form.getEmail().trim().toLowerCase());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        user.setRole(Role.USER);

        User savedUser = userRepository.save(user);
        auditLogService.logOperation(savedUser, "REGISTRATION", "User registered with email: " + savedUser.getEmail(), OperationType.REGISTRATION);
        return savedUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public User updateProfile(User user, ProfileUpdateForm form) {
        String normalizedEmail = form.getEmail().trim().toLowerCase();
        userRepository.findByEmail(normalizedEmail)
                .filter(existingUser -> !existingUser.getId().equals(user.getId()))
                .ifPresent(existingUser -> {
                    throw new IllegalArgumentException("Email already exists");
                });

        user.setUsername(form.getUsername().trim());
        user.setEmail(normalizedEmail);
        User updatedUser = userRepository.save(user);
        auditLogService.logOperation(updatedUser, "PROFILE_UPDATED", "Profile updated for user: " + updatedUser.getEmail(), OperationType.PROFILE_UPDATED);
        return updatedUser;
    }
}
