package main.java.ui;

import java.util.Objects;

/**
 * Author: Artyom Aroyan
 * Date: 23.06.26
 * Time: 16:21:54
 */
public enum MainMenuOption {
    ADD_EXPENSE("add"),
    UPDATE_EXPENSE("update"),
    DELETE_EXPENSE("delete"),
    FETCH_BY_ID("fetch by id"),
    FETCH_BY_TITLE("fetch by title"),
    FETCH_BY_AMOUNT("fetch by amount"),
    FETCH_BY_CATEGORY("fetch by category"),
    FETCH_BY_AMOUNT_AND_CATEGORY("fetch by amount and category"),
    FETCH_ALL("fetch all"),
    EXIT("exit");

    private final String value;

    MainMenuOption(String value) {
        this.value = value;
    }

    public static MainMenuOption fromString(String input) {
        Objects.requireNonNull(input, "input must not be null");
        String cleanedInput = input.trim().toLowerCase();

        for (MainMenuOption option : values()) {
            if (option.value.equals(cleanedInput)) {
                return option;
            }
        }
        try {
            return valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Unknown menu option: " + input);
        }
    }
}