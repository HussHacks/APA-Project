package com.fittrack.fittrackpro.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fittrack.fittrackpro.entity.AuditLog;
import com.fittrack.fittrackpro.entity.OperationType;
import com.fittrack.fittrackpro.entity.User;
import com.fittrack.fittrackpro.repository.AuditLogRepository;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void logOperation(User user, String action, String description, OperationType operationType) {
        AuditLog auditLog = new AuditLog(user, action, description, operationType);
        auditLogRepository.save(auditLog);
    }

    public List<AuditLog> getUserHistory(User user) {
        return auditLogRepository.findByUserOrderByTimestampDesc(user);
    }

    public List<AuditLog> getOperationsHistory(LocalDateTime startDate, LocalDateTime endDate) {
        return auditLogRepository.findByTimestampBetweenOrderByTimestampDesc(startDate, endDate);
    }

    public List<AuditLog> getAllAuditLogs() {
        return auditLogRepository.findAll();
    }
}
