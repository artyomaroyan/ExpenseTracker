package main.java.model;

import java.time.Instant;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 22:35:01
 */
public record Expense(
        Long id,
        String title,
        String description,
        double amount,
        Category category,
        Instant createdAt
) {
}