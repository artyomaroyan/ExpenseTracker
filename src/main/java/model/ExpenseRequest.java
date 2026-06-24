package main.java.model;

/**
 * Author: Artyom Aroyan
 * Date: 24.06.26
 * Time: 00:09:28
 */
public record ExpenseRequest(
        String title,
        String description,
        double amount,
        String  category
) {
}