package main.java.ui;

import main.java.validation.InputValidator;

import java.util.Scanner;

import static java.lang.IO.println;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 23:23:43
 */
public class UserInput implements AutoCloseable {
    private final Scanner scanner;
    private boolean closed = false;

    private final InputValidator inputValidator;

    public UserInput(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
        this.scanner = new Scanner(System.in);
    }

    public Long readLong() {
        chackedClosed();
        println("Please enter expense ID:");
        Long id = scanner.nextLong();
        inputValidator.validateId(id);
        return id;
    }

    public String readTitle() {
        chackedClosed();
        println("Please enter expense title:");
        return scanner.next();
    }

    public String readDescription() {
        chackedClosed();
        println("Please enter expense title:");
        return scanner.next();
    }

    public String readCategory() {
        chackedClosed();
        println("Please choose category: 'SHOPPING', 'FOOD', 'HOUSE_RENT', 'CAR', 'OTHER'");
        return scanner.next();
    }

    @Override
    public void close() throws Exception {
        if (!closed) {
            scanner.close();
            closed = true;
        }
    }

    private void chackedClosed() {
        if (closed) throw new UnsupportedOperationException("Input handler is closed!");
    }
}