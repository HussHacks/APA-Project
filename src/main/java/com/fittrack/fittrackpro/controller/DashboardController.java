package com.fittrack.fittrackpro.controller;

import java.time.LocalDate;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fittrack.fittrackpro.dto.MealSummaryDTO;
import com.fittrack.fittrackpro.dto.ProfileUpdateForm;
import com.fittrack.fittrackpro.entity.Meal;
import com.fittrack.fittrackpro.entity.User;
import com.fittrack.fittrackpro.entity.Workout;
import com.fittrack.fittrackpro.service.AuditLogService;
import com.fittrack.fittrackpro.service.MealService;
import com.fittrack.fittrackpro.service.UserService;
import com.fittrack.fittrackpro.service.WorkoutService;

@Controller
public class DashboardController {

    private final UserService userService;
    private final MealService mealService;
    private final WorkoutService workoutService;
    private final AuditLogService auditLogService;

    public DashboardController(
            UserService userService,
            MealService mealService,
            WorkoutService workoutService,
            AuditLogService auditLogService) {
        this.userService = userService;
        this.mealService = mealService;
        this.workoutService = workoutService;
        this.auditLogService = auditLogService;
    }

    @GetMapping("/dashboard")
    public String dashboard(java.security.Principal principal, Model model) {
        User user = userService.getUserByEmail(principal.getName());
        LocalDate today = LocalDate.now();
        MealSummaryDTO summary = mealService.getSummary(user.getId(), today);

        model.addAttribute("currentUser", user);
        model.addAttribute("today", today);
        model.addAttribute("summary", summary);
        model.addAttribute("meal", new Meal());
        model.addAttribute("workout", new Workout());
        model.addAttribute("meals", mealService.getMealsByUser(user.getId()));
        model.addAttribute("workouts", workoutService.getWorkoutsByUser(user.getId()));
        model.addAttribute("history", auditLogService.getUserHistory(user));

        if (user.getRole().name().equals("ADMIN")) {
            model.addAttribute("allUsers", userService.getAllUsers());
            model.addAttribute("allAuditLogs", auditLogService.getAllAuditLogs());
            return "admin-dashboard";
        }

        return "dashboard";
    }

    @GetMapping("/profile")
    public String profile(java.security.Principal principal, Model model) {
        User user = userService.getUserByEmail(principal.getName());
        ProfileUpdateForm profileForm = new ProfileUpdateForm();
        profileForm.setUsername(user.getUsername());
        profileForm.setEmail(user.getEmail());

        model.addAttribute("currentUser", user);
        model.addAttribute("profileForm", profileForm);
        model.addAttribute("history", auditLogService.getUserHistory(user));

        if (user.getRole().name().equals("ADMIN")) {
            return "admin-profile";
        }

        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(java.security.Principal principal,
                                @Valid @ModelAttribute("profileForm") ProfileUpdateForm profileForm,
                                org.springframework.validation.BindingResult bindingResult,
                                Model model) {
        User user = userService.getUserByEmail(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("currentUser", user);
            model.addAttribute("history", auditLogService.getUserHistory(user));
            return user.getRole().name().equals("ADMIN") ? "admin-profile" : "profile";
        }

        try {
            userService.updateProfile(user, profileForm);
            return "redirect:/profile?updated=true";
        } catch (IllegalArgumentException ex) {
            model.addAttribute("currentUser", user);
            model.addAttribute("history", auditLogService.getUserHistory(user));
            model.addAttribute("profileError", ex.getMessage());
            return user.getRole().name().equals("ADMIN") ? "admin-profile" : "profile";
        }
    }

    @PostMapping("/dashboard/reset-meals")
    public String resetMeals(java.security.Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        mealService.resetMealsByUser(user.getId());
        return "redirect:/dashboard?resetMeals=true";
    }

    @PostMapping("/dashboard/reset-workouts")
    public String resetWorkouts(java.security.Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        workoutService.resetWorkoutsByUser(user.getId());
        return "redirect:/dashboard?resetWorkouts=true";
    }
}
