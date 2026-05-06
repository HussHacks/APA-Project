package com.fittrack.fittrackpro.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fittrack.fittrackpro.entity.User;
import com.fittrack.fittrackpro.entity.Workout;
import com.fittrack.fittrackpro.service.UserService;
import com.fittrack.fittrackpro.service.WorkoutService;

@Controller
public class WorkoutController {

    private final WorkoutService workoutService;
    private final UserService userService;

    public WorkoutController(WorkoutService workoutService, UserService userService) {
        this.workoutService = workoutService;
        this.userService = userService;
    }

    @PostMapping("/workouts")
    public String addWorkout(java.security.Principal principal,
                             @Valid @ModelAttribute("workout") Workout workout,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("workoutError", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/dashboard";
        }

        User user = userService.getUserByEmail(principal.getName());
        workoutService.addWorkout(user.getId(), workout);
        redirectAttributes.addFlashAttribute("workoutSuccess", "Workout saved successfully");
        return "redirect:/dashboard";
    }
}
