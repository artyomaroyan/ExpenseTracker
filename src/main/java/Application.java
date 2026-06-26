package main.java;

import main.java.configuration.ApplicationConfiguration;
import main.java.context.ApplicationContext;
import main.java.ui.ConsoleUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 22:34:38
 */
public class Application {
    private static final Logger log = LoggerFactory.getLogger(Application.class);
    static void main() {
        try {
            ApplicationConfiguration configuration = ApplicationConfiguration.load();
            ApplicationContext context = ApplicationContext.initialize(configuration);
            ConsoleUI consoleUI = context.consoleUI();
            consoleUI.start();
        } catch (Exception ex) {
            log.error("Failed to produce request: {}", ex.getMessage(), ex);
            throw new RuntimeException("Application failed: " + ex.getMessage());
        }
    }
}