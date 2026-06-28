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

    public UserInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String readLine() {
        chackClosed();
        return scanner.nextLine();
    }

    public String readLine(String prompt) {
        println(prompt + " ");
        return this.readLine();
    }

    public Number readDigit(String prompt) {
            try {
                var input = this.readLine(prompt);
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                throw new IllegalStateException("Invalid number.");
            }
    }

    @Override
    public void close() {
        if (!closed) {
            scanner.close();
            closed = true;
        }
    }

    private void chackClosed() {
        if (closed) throw new UnsupportedOperationException("Input handler is closed!");
    }
}

//    public Long readLong() {
//        chackClosed();
//        println("Please enter expense ID:");
//        return scanner.nextLong();
//    }
//
//    public String readTitle() {
//        chackClosed();
//        println("Please enter expense title:");
//        var input =  scanner.nextLine();
//        inputValidator.validateTitle(input);
//        return input;
//    }
//
//    public String readDescription() {
//        chackClosed();
//        println("Please enter expense description:");
//        var input = scanner.nextLine();
//        inputValidator.validateDescription(input);
//        return input;
//    }
//
//    public double readAmount() {
//        chackClosed();
//        println("Please enter your expense amount:");
//        var input = scanner.nextDouble();
//        inputValidator.validateAmount(input);
//        return input;
//    }
//
//    public String readCategory() {
//        chackClosed();
//        println("Please choose category: 'SHOPPING', 'FOOD', 'HOUSE_RENT', 'CAR', 'OTHER'");
//        var input = scanner.nextLine();
//        inputValidator.validateCategory(input);
//        return input.toUpperCase();
//    }