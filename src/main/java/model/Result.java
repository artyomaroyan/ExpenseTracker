package main.java.model;

import java.util.Collections;
import java.util.List;

/**
 * Author: Artyom Aroyan
 * Date: 23.06.26
 * Time: 23:56:41
 */
public final class Result {
    private final boolean success;
    private final String message;
    private final Expense expense;
    private final List<String> errors;

    public Result(boolean success, String message, Expense expense, List<String> errors) {
        this.success = success;
        this.message = message;
        this.expense = expense;
        this.errors = errors != null ? Collections.unmodifiableList(errors) : Collections.emptyList();
    }

    public static Result success(String message, Expense expense) {
        return null;
    }
}