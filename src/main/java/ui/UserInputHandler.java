package main.java.ui;

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
        checkClosed();
        return scanner.nextLine();
    }

    public String readLine(String prompt) {
        println(prompt + " ");
        return this.readLine();
    }

    public Long readLong(String prompt) {
        try {
            var input = this.readLine(prompt);
            return Long.parseLong(input);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid number format: ", ex);
        }
    }

    public double readDouble(String prompt) {
        try {
            var input = this.readLine(prompt);
            return Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid number: ", ex);
        }
    }

    @Override
    public void close() {
        if (!closed) {
            scanner.close();
            closed = true;
        }
    }

    private void checkClosed() {
        if (closed) throw new UnsupportedOperationException("Input handler is closed!");
    }
}