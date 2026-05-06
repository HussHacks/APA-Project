package com.fittrack.fittrackpro.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fittrack.fittrackpro.entity.OperationType;
import com.fittrack.fittrackpro.entity.User;
import com.fittrack.fittrackpro.entity.Workout;
import com.fittrack.fittrackpro.repository.UserRepository;
import com.fittrack.fittrackpro.repository.WorkoutRepository;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;
    private final AuditLogService auditLogService;

    public WorkoutService(WorkoutRepository workoutRepository, UserRepository userRepository, AuditLogService auditLogService) {
        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
        this.auditLogService = auditLogService;
    }

    public Workout addWorkout(Long userId, Workout workout) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        workout.setUser(user);
        Workout savedWorkout = workoutRepository.save(workout);
        auditLogService.logOperation(user, "WORKOUT_CREATED", "Created workout: " + workout.getName() + " (" + workout.getSets() + "x" + workout.getReps() + ")", OperationType.WORKOUT_CREATED);
        return savedWorkout;
    }

    public List<Workout> getWorkoutsByUser(Long userId) {
        return workoutRepository.findByUserId(userId);
    }

    public double getTotalVolumeByUser(Long userId) {
        List<Workout> workouts = workoutRepository.findByUserId(userId);
        double totalVolume = 0;

        for (Workout workout : workouts) {
            totalVolume += workout.getSets() * workout.getReps() * workout.getWeight();
        }

        return totalVolume;
    }

    @Transactional
    public void resetWorkoutsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        workoutRepository.deleteByUserId(userId);
        auditLogService.logOperation(user, "DATA_RESET", "Reset all workout entries", OperationType.DATA_RESET);
    }
}

