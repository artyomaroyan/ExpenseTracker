package main.java;

import main.java.service.ExpenseLoader;
import main.java.service.ExpenseLoaderImpl;
import main.java.ui.UserInputHandler;
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

        try (UserInputHandler input = new UserInputHandler(inputValidator)) {
            println(input.readLong());
            println(input.readTitle());
            println(input.readDescription());
            println(input.readCategory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}