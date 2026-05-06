package com.fittrack.fittrackpro.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fittrack.fittrackpro.dto.MealSummaryDTO;
import com.fittrack.fittrackpro.entity.Meal;
import com.fittrack.fittrackpro.entity.OperationType;
import com.fittrack.fittrackpro.entity.User;
import com.fittrack.fittrackpro.repository.MealRepository;
import com.fittrack.fittrackpro.repository.UserRepository;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditLogService auditLogService;

    public Meal createMeal(Long userId, Meal meal) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        meal.setUser(user);
        Meal savedMeal = mealRepository.save(meal);
        auditLogService.logOperation(user, "MEAL_CREATED", "Created meal: " + meal.getName() + " (" + meal.getCalories() + " kcal)", OperationType.MEAL_CREATED);
        return savedMeal;
    }

    public List<Meal> getMealsByUser(Long userId) {
        return mealRepository.findByUserId(userId);
    }

    public int getTotalCalories(Long userId, LocalDate date) {
        List<Meal> meals = mealRepository.findByUserIdAndDate(userId, date);

        int total = 0;
        for (Meal meal : meals) {
            total += meal.getCalories();
        }

        return total;
    }

    public int getTotalProtein(Long userId, LocalDate date) {
        List<Meal> meals = mealRepository.findByUserIdAndDate(userId, date);

        int total = 0;
        for (Meal meal : meals) {
            total += meal.getProtein();
        }

        return total;
    }

    public MealSummaryDTO getSummary(Long userId, LocalDate date) {
        List<Meal> meals = mealRepository.findByUserIdAndDate(userId, date);

        int calories = 0;
        int protein = 0;
        int carbs = 0;
        int fats = 0;

        for (Meal meal : meals) {
            calories += meal.getCalories();
            protein += meal.getProtein();
            carbs += meal.getCarbs();
            fats += meal.getFats();
        }

        return new MealSummaryDTO(calories, protein, carbs, fats);
    }

    @Transactional
    public void resetMealsByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        mealRepository.deleteByUserId(userId);
        auditLogService.logOperation(user, "DATA_RESET", "Reset all meal entries", OperationType.DATA_RESET);
    }
}

