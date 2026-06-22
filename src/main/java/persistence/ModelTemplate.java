package main.java.persistence;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 21:59:21
 */
public class ModelTemplate implements ModelOperation {
    private static final Logger log = LoggerFactory.getLogger(ModelTemplate.class);

    private final File file = new File("Expense.json");
    private final Gson gson;

    public ModelTemplate() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Instant.class, (JsonSerializer<Instant>) (src, _, _) ->
                        new JsonPrimitive(src.toString()))
                .registerTypeAdapter(Instant.class, (JsonDeserializer<Instant>) (json, _, _) ->
                        Instant.parse(json.getAsString()))
                .create();
    }

    @Override
    public <T> T insert(T model) {
        Objects.requireNonNull(model, "Model must not be null");
        Type listType = TypeToken.getParameterized(List.class, model.getClass()).getType();
        List<T> values;

        if (!file.exists() || file.length() == 0) {
            log.error("Failed to read file: File does not exists or is empty: {}", file.getAbsolutePath());
            throw new IllegalStateException("Can not read file: " + file.getAbsolutePath());
        }

        // 1. read JSON file before saving any new data, otherwise all data will be removed from file.
        try (Reader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            values = gson.fromJson(reader, listType);

            if (values == null) {
                values = new ArrayList<>();
            }

            // 2. after reading file you can add new data without losing old data.
            values.add(model);
            try (Writer writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
                gson.toJson(values, writer);
            }
        } catch (IOException | JsonSyntaxException ex) {
            log.error("Failed to load JSON file: {}", file.getAbsolutePath(), ex);
            throw new RuntimeException("Reading process failed: " + file.getAbsolutePath(), ex);
        }
        return model;
    }

    @Override
    public <T> T update(T model) {
        return null;
    }
}