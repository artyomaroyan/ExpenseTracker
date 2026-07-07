package main.java.model;

import java.util.Objects;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 23:21:09
 */
public enum Category {
    SHOPPING("shopping"),
    FOOD("food"),
    HOUSE_RENT("house rent"),
    CAR("car"),
    OTHER("other");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public static Category fromString(String input) {
        Objects.requireNonNull(input, "input must not be null");
        var cleaned = input.trim().toLowerCase();

        for (Category category : values()) {
            if (category.value.equals(cleaned)) {
                return category;
            }
        }
        try {
            return valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Unknown category: ", ex);
        }
    }
}