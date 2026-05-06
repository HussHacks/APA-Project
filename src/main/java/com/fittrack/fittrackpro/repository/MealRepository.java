package com.fittrack.fittrackpro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fittrack.fittrackpro.entity.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findByUserId(Long userId);
    List<Meal> findByUserIdAndDate(Long userId, LocalDate date);
    void deleteByUserId(Long userId);
}
