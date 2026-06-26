package main.java.validation;

import main.java.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 23:36:07
 */
public class InputValidatorImpl implements InputValidator {
    private static final Logger log = LoggerFactory.getLogger(InputValidatorImpl.class);

    private static final int TITLE_MIN_LENGTH = 3;
    private static final int TITLE_MAX_LENGTH = 20;
    private static final double MIN_AMOUNT = 1.0;
    private static final double MAX_AMOUNT = 999999.9;
    private static final int DESCRIPTION_MIN_LENGTH = 5;
    private static final int DESCRIPTION_MAX_LENGTH = 50;

//    @Override
//    public void validateId(Long id) {
//        boolean exists = expenseLoaderService.loadAll().stream()
//                .anyMatch(e -> Objects.equals(e.id(), id));
//        if (exists) throw new IllegalArgumentException("Duplicate ID value: " + id);
//    }

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
    public void validateAmount(double amount) {
        if (amount <= MIN_AMOUNT) {
            log.warn("Amount can not be les or equal to {}", MIN_AMOUNT);
            throw new IllegalStateException("Amount is less then allowed: Please enter valid amount.");
        } else if (amount >= MAX_AMOUNT) {
            log.warn("Amount can not be greater or equal to {}", MAX_AMOUNT);
            throw new IllegalStateException("Amount is greater then allowed: Please enter valid amount.");
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