package com.fittrack.fittrackpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fittrack.fittrackpro.entity.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUserId(Long userId);
    void deleteByUserId(Long userId);
}
