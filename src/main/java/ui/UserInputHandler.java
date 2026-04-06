package main.java.ui;

import java.util.Scanner;

/**
 * Author: Artyom Aroyan
 * Date: 06.04.26
 * Time: 18:07:38
 */
public class UserInputHandler implements AutoCloseable {
    private final Scanner scanner;
    private boolean closed = false;

    public UserInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void close() throws Exception {
        if (!closed) {
            scanner.close();
            closed = true;
        }
    }
}