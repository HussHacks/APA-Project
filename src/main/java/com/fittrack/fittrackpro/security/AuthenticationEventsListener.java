package com.fittrack.fittrackpro.security;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.fittrack.fittrackpro.entity.OperationType;
import com.fittrack.fittrackpro.entity.User;
import com.fittrack.fittrackpro.repository.UserRepository;
import com.fittrack.fittrackpro.service.AuditLogService;

@Component
public class AuthenticationEventsListener {

    private final UserRepository userRepository;
    private final AuditLogService auditLogService;

    public AuthenticationEventsListener(UserRepository userRepository, AuditLogService auditLogService) {
        this.userRepository = userRepository;
        this.auditLogService = auditLogService;
    }

    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        User user = findUser(event.getAuthentication());
        if (user != null) {
            auditLogService.logOperation(user, "LOGIN", "User logged in successfully", OperationType.LOGIN);
        }
    }

    @EventListener
    public void handleLogoutSuccess(LogoutSuccessEvent event) {
        User user = findUser(event.getAuthentication());
        if (user != null) {
            auditLogService.logOperation(user, "LOGOUT", "User logged out successfully", OperationType.LOGOUT);
        }
    }

    private User findUser(Authentication authentication) {
        if (authentication == null || authentication.getName() == null) {
            return null;
        }
        return userRepository.findByEmail(authentication.getName()).orElse(null);
    }
}
