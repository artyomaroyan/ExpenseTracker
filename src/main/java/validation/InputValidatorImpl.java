package main.java.validation;

import main.java.model.Category;
import main.java.service.ExpenseLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Objects;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 23:36:07
 */
public class InputValidatorImpl implements InputValidator {
    private static final Logger log = LoggerFactory.getLogger(InputValidatorImpl.class);

    private static final int TITLE_MIN_LENGTH = 3;
    private static final int TITLE_MAX_LENGTH = 20;
    private static final int DESCRIPTION_MIN_LENGTH = 5;
    private static final int DESCRIPTION_MAX_LENGTH = 50;

    private final ExpenseLoader expenseLoader;

    public InputValidatorImpl(ExpenseLoader expenseLoader) {
        this.expenseLoader = expenseLoader;
    }

    @Override
    public void validateId(Long id) {
        boolean exists = expenseLoader.loadAll().stream()
                .anyMatch(e -> Objects.equals(e.id(), id));
        if (exists) throw new IllegalArgumentException("Duplicate ID value: " + id);
    }

    @Override
    public void validateTitle(String title) {
         if (title.length() <= TITLE_MIN_LENGTH) {
             log.warn("Title can not be less then 3 characters.");
             throw new IllegalStateException("Title length is less then allowed");
         } else if (title.length() >= TITLE_MAX_LENGTH) {
             log.warn("Title length can not be greater then 20 characters.");
             throw new IllegalStateException("Title length is greater than allowed");
         }
    }

    @Override
    public void validateDescription(String description) {
        if (description.length() <= DESCRIPTION_MIN_LENGTH) {
            log.warn("Description length can not be less then 5 characters.");
            throw new IllegalStateException("Description length is less then allowed");
        } else if (description.length() >= DESCRIPTION_MAX_LENGTH) {
            log.warn("Description length can not be greater then 50 characters.");
            throw new IllegalStateException("Description length is greater then allowed");
        }
    }

    @Override
    public void validateCategory(String category) {
        if (Arrays.stream(Category.values())
                .noneMatch(c -> c.name().equalsIgnoreCase(category))) {
            log.warn("Category mismatch: {}", category);
            throw new IllegalStateException("Please select category from list.");
        }
    }
}