package main.java.persistence;

/**
 * Author: Artyom Aroyan
 * Date: 24.06.26
 * Time: 04:43:22
 */
public class PersistenceException extends RuntimeException {
    public PersistenceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}