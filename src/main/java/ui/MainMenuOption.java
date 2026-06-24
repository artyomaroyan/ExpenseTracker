package main.java.ui;

/**
 * Author: Artyom Aroyan
 * Date: 23.06.26
 * Time: 16:21:54
 */
public enum MainMenuOption {
    ADD_EXPENSE,
    UPDATE_EXPENSE,
    DELETE_EXPENSE,
    FETCH_BY_ID,
    FETCH_BY_TITLE,
    FETCH_BY_AMOUNT,
    FETCH_BY_CATEGORY,
    FETCH_BY_AMOUNT_AND_CATEGORY,
    FETCH_ALL,
    EXIT;

    public static MainMenuOption fromString(String input) {
        return valueOf(input.trim().toUpperCase());
    }
}