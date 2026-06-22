package main.java;

import main.java.service.ExpenseLoader;
import main.java.service.ExpenseLoaderImpl;
import main.java.ui.UserInput;
import main.java.validation.InputValidator;
import main.java.validation.InputValidatorImpl;

import static java.lang.IO.println;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 22:34:38
 */
public class Application {
    static void main() {
        ExpenseLoader expenseLoader = new ExpenseLoaderImpl();
        InputValidator inputValidator = new InputValidatorImpl(expenseLoader);

        try (UserInput input = new UserInput(inputValidator)) {
            println(input.readLong());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}