package com.fittrack.fittrackpro.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Meal name is required")
    private String name;

    @NotNull(message = "Calories is required")
    @Min(value = 0, message = "Calories must be non-negative")
    private int calories;

    @NotNull(message = "Protein is required")
    @Min(value = 0, message = "Protein must be non-negative")
    private int protein;

    @NotNull(message = "Carbs is required")
    @Min(value = 0, message = "Carbs must be non-negative")
    private int carbs;

    @NotNull(message = "Fats is required")
    @Min(value = 0, message = "Fats must be non-negative")
    private int fats;

    @NotBlank(message = "Meal type is required")
    private String mealType;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
