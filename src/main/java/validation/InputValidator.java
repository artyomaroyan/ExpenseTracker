package main.java.validation;

/**
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 00:16:44
 */
public interface InputValidator {
    void validateId(Long id);
    void validateTitle(String title);
    void validateDescription(String description);
    void validateCategory(String category);
}