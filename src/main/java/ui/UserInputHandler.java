package main.java.ui;

import main.java.validation.InputValidator;

import java.util.Scanner;

import static java.lang.IO.println;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 23:23:43
 */
public class UserInputHandler implements AutoCloseable {
    private final Scanner scanner;
    private boolean closed = false;

    private final InputValidator inputValidator;

    public UserInputHandler(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
        this.scanner = new Scanner(System.in);
    }

    public Long readLong() {
        chackedClosed();
        println("Please enter expense ID:");
        var input = scanner.nextLong();
        inputValidator.validateId(input);
        return input;
    }

    public String readTitle() {
        chackedClosed();
        println("Please enter expense title:");
        var input =  scanner.next();
        inputValidator.validateTitle(input);
        return input;
    }

    public String readDescription() {
        chackedClosed();
        println("Please enter expense description:");
        var input = scanner.next();
        inputValidator.validateDescription(input);
        return input;
    }

    public double readAmount() {
        chackedClosed();
        println("Please enter your expense amount:");
        var input = scanner.nextDouble();
        inputValidator.validateAmount(input);
        return input;
    }

    public String readCategory() {
        chackedClosed();
        println("Please choose category: 'SHOPPING', 'FOOD', 'HOUSE_RENT', 'CAR', 'OTHER'");
        var input = scanner.next();
        inputValidator.validateCategory(input);
        return input.toUpperCase();
    }

    @Override
    public void close() {
        if (!closed) {
            scanner.close();
            closed = true;
        }
    }

    private void chackedClosed() {
        if (closed) throw new UnsupportedOperationException("Input handler is closed!");
    }
}