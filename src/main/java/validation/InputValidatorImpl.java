package main.java.validation;

import main.java.service.ExpenseLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}