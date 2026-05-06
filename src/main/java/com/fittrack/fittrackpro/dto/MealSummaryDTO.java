package com.fittrack.fittrackpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealSummaryDTO {
    private int calories;
    private int protein;
    private int carbs;
    private int fats;
}
