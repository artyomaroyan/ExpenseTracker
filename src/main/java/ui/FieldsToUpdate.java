package main.java.ui;

/**
 * Author: Artyom Aroyan
 * Date: 24.06.26
 * Time: 00:50:19
 */
public enum FieldsToUpdate {
    TITLE,
    DESCRIPTION,
    AMOUNT,
    CATEGORY;

    public static FieldsToUpdate fromString(String value) {
        return valueOf(value.trim().toUpperCase());
    }
}