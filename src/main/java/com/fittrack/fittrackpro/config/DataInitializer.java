package com.fittrack.fittrackpro.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fittrack.fittrackpro.entity.Role;
import com.fittrack.fittrackpro.entity.User;
import com.fittrack.fittrackpro.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            createUserIfMissing(userRepository, passwordEncoder, "admin", "admin@fittrack.com", "Admin123!", Role.ADMIN);
            createUserIfMissing(userRepository, passwordEncoder, "john_athlete", "john@example.com", "Password123!", Role.USER);
            createUserIfMissing(userRepository, passwordEncoder, "jane_fitness", "jane@example.com", "Password456!", Role.USER);
        };
    }

    private void createUserIfMissing(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                     String username, String email, String rawPassword, Role role) {
        if (userRepository.findByEmail(email).isPresent()) {
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        userRepository.save(user);
    }
}
