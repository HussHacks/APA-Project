package com.fittrack.fittrackpro.controller;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fittrack.fittrackpro.entity.Meal;
import com.fittrack.fittrackpro.entity.User;
import com.fittrack.fittrackpro.service.MealService;
import com.fittrack.fittrackpro.service.UserService;

@Controller
public class MealController {

    private final MealService mealService;
    private final UserService userService;

    public MealController(MealService mealService, UserService userService) {
        this.mealService = mealService;
        this.userService = userService;
    }

    @PostMapping("/meals")
    public String addMeal(java.security.Principal principal,
                          @Valid @ModelAttribute("meal") Meal meal,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mealError", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/dashboard";
        }

        User user = userService.getUserByEmail(principal.getName());
        mealService.createMeal(user.getId(), meal);
        redirectAttributes.addFlashAttribute("mealSuccess", "Meal saved successfully");
        return "redirect:/dashboard";
    }
}
