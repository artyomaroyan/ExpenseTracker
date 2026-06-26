package main.java.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

/**
 * Loads and exposes application configuration from {@code application.properties},
 * with environment-variable overrides.
 *
 * <p>The class is immutable after construction. All property access is through
 * typed getter methods rather than raw {@link Properties#getProperty} calls at
 * the call-site, so the rest of the application never has to know the key names.

 * Author: Artyom Aroyan
 * Date: 23.06.26
 * Time: 23:04:13
 */
public class ApplicationConfiguration {
    private static final String PROPERTY_FILE = "application.properties";
    private static final String KEY_EXPENSE_FILE_PATH = "expense.file.path";
    private static final String ENV_EXPENSE_FILE_PATH = "EXPENSE_FILE_PATH";
    private static final String DEFAULT_EXPENSE_FILE_PATH = System.getProperty("user.dir") + "/resources/Expense.json";

    private final Properties properties;

    private ApplicationConfiguration(Properties properties) {
        this.properties = Objects.requireNonNull(properties, "properties must not be null");
    }

    /**
     * Loads configuration from the classpath resource {@code application.properties}.
     * An environment variable ({@value #ENV_EXPENSE_FILE_PATH}) overrides the file value.
     *
     * @throws ApplicationConfigurationException if the properties file cannot be read
     */
    public static ApplicationConfiguration load() {
       Properties props = loadPropertiesFile();
       applyEnvironmentOverrides(props);
       return new ApplicationConfiguration(props);
    }

    /**
     * Returns the path to the expense JSON file as a {@link Path}.
     * Resolves from the environment variable, then the properties file,
     * then the built-in default.
     */
    public Path getExpenseFilePath() {
        return Path.of(properties.getProperty(KEY_EXPENSE_FILE_PATH, DEFAULT_EXPENSE_FILE_PATH));
    }

    // ------------------------------------------------------------------
    // Private helpers
    // ------------------------------------------------------------------

    private static Properties loadPropertiesFile() {
        Properties props = new Properties();
        try (InputStream inputStream = ApplicationConfiguration.class
                .getClassLoader()
                .getResourceAsStream(PROPERTY_FILE)) {

            if (inputStream == null) {
                return props;
            }
            props.load(inputStream);
        } catch (IOException ex) {
            throw new ApplicationConfigurationException("Failed to load configuration from classpath resource: " + PROPERTY_FILE, ex);
        }
        return props;
    }

    private static void applyEnvironmentOverrides(Properties properties) {
        String envPath = System.getenv(ENV_EXPENSE_FILE_PATH);
        if (envPath != null && envPath.isBlank()) {
            properties.setProperty(KEY_EXPENSE_FILE_PATH, envPath);
        }
    }
}