package main.java.model;

import java.time.LocalDate;

/**
 * Author: Artyom Aroyan
 * Date: 06.04.26
 * Time: 17:46:01
 */
public record Expense(
        Long id,
        double amount,
        Category category,
        String description,
        LocalDate date
) {
}