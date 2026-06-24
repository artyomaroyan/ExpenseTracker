package main.java.configuration;

/**
 * Author: Artyom Aroyan
 * Date: 24.06.26
 * Time: 05:27:14
 */
public class ApplicationConfigurationException extends RuntimeException {
    public ApplicationConfigurationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}