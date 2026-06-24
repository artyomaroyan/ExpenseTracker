package main.java.persistence;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;

/**
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 21:59:21
 */
public class ModelTemplate<T, ID> implements ModelOperations<T, ID> {
    private static final Logger log = LoggerFactory.getLogger(ModelTemplate.class);

    private final Path filePath;
    private final Class<T> modelClass;
    private final ModelInformation<T, ID> modelInformation;
    private final Gson gson;

    /**
     * @param filePath          path to the backing JSON file; created automatically
     *                          on first write if it does not yet exist
     * @param modelClass        the {@link Class} of the domain model (needed for
     *                          Gson's generic type resolution)
     * @param modelInformation  provides id-extraction for {@link #findById} and
     *                          id-comparison for {@link #update}
     */
    public ModelTemplate(Path filePath, Class<T> modelClass, ModelInformation<T, ID> modelInformation) {
        this.filePath = filePath;
        this.modelClass = modelClass;
        this.modelInformation = modelInformation;
        this.gson = buildGson();
    }

    @Override
    public T insert(T model) {
        Objects.requireNonNull(model, "model must not be null");
        List<T> records = readAll();
        records.add(model);
        writeAll(records);

        log.debug("Inserted record into {}", filePath);
        return model;
    }

    @Override
    public T update(T model) {
        Objects.requireNonNull(model, "model must not be null");
        ID targetId = modelInformation.getId(model);
        List<T> records = readAll();

        boolean found = false;
        for (int i = 0; i < readAll().size(); i++) {
            if (targetId.equals(modelInformation.getId(records.get(i)))) {
                records.set(i, model);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new NoSuchElementException("No record with id=" + targetId + " found in " + filePath);
        }

        writeAll(records);
        log.debug("Updated record with id={} in {}", targetId, filePath);
        return model;
    }

    @Override
    public List<T> findAll() {
        return readAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        Objects.requireNonNull(id, "id must not be null");
        return readAll().stream()
                .filter(record -> id.equals(modelInformation.getId(record)))
                .findFirst();
    }

    // ------------------------------------------------------------------
    // Internal helpers
    // ------------------------------------------------------------------

    /**
     * Reads the entire JSON file into a mutable list.
     * Returns an empty list when the file does not exist or is empty —
     * treating "no file yet" as a valid initial state rather than an error.
     */
    private List<T> readAll() {
        if (!Files.exists(filePath) || isFileEmpty()) {
            log.debug("File not found or empty, returning empty list: {}", filePath);
            return new ArrayList<>();
        }

        Type listType = TypeToken.getParameterized(List.class, modelClass).getType();

        try (Reader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
            List<T> result = gson.fromJson(reader, listType);
            return result != null ? result : new ArrayList<>();
        } catch (IOException ex) {
            log.error("Failed to read JSON file: {}", filePath, ex);
            throw new PersistenceException("Could not read from " + filePath, ex);
        } catch (JsonSyntaxException ex) {
            throw new PersistenceException("Corrupted JSON in " + filePath, ex);
        }
    }

    /**
     * Serialises {@code records} back to the JSON file, creating parent
     * directories and the file itself if they do not yet exist.
     */
    private void writeAll(List<T> records) {
        try {
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }

            try (Writer writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
                gson.toJson(records, writer);
            }
        } catch (IOException ex) {
            log.error("Failed to write JSON file: {}", filePath, ex);
            throw new PersistenceException("Could not write to " + filePath, ex);
        }
    }

    private boolean isFileEmpty() {
        try {
            return Files.size(filePath) == 0;
        } catch (IOException ex) {
            return true;
        }
    }

    private static Gson buildGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Instant.class, (JsonSerializer<Instant>) (src, _, _) ->
                        new JsonPrimitive(src.toString()))
                .registerTypeAdapter(Instant.class, (JsonDeserializer<Instant>) (json, _, _) ->
                        Instant.parse(json.getAsString()))
                .create();
    }
}

//    private static final Logger log = LoggerFactory.getLogger(ModelTemplate.class);
//    private static final File FILE = new File(ApplicationConfiguration.load().getExpenseFilePath());
//    //    private final File file = new File("Expense.json");
//    private final Gson gson;
//
//    public ModelTemplate() {
//        this.gson = new GsonBuilder()
//                .setPrettyPrinting()
//                .registerTypeAdapter(Instant.class, (JsonSerializer<Instant>) (src, _, _) ->
//                        new JsonPrimitive(src.toString()))
//                .registerTypeAdapter(Instant.class, (JsonDeserializer<Instant>) (json, _, _) ->
//                        Instant.parse(json.getAsString()))
//                .create();
//    }
//
//    @Override
//    public <T> T insert(T model) {
//        Objects.requireNonNull(model, "Model must not be null");
//        Type listType = TypeToken.getParameterized(List.class, model.getClass()).getType();
//        List<T> values;
//
//        if (!FILE.exists() || FILE.length() == 0) {
//            log.error("Failed to read file: File does not exists or is empty: {}", FILE.getAbsolutePath());
//            throw new IllegalStateException("Can not read file: " + FILE.getAbsolutePath());
//        }
//
//        // 1. read JSON file before saving any new data, otherwise all data will be removed from file.
//        try (Reader reader = Files.newBufferedReader(FILE.toPath(), StandardCharsets.UTF_8)) {
//            values = gson.fromJson(reader, listType);
//
//            if (values == null) {
//                values = new ArrayList<>();
//            }
//
//            // 2. after reading file you can add new data without losing old data.
//            values.add(model);
//            try (Writer writer = Files.newBufferedWriter(FILE.toPath(), StandardCharsets.UTF_8)) {
//                gson.toJson(values, writer);
//            }
//        } catch (IOException | JsonSyntaxException ex) {
//            log.error("Failed to load JSON file: {}", FILE.getAbsolutePath(), ex);
//            throw new RuntimeException("Reading process failed: " + FILE.getAbsolutePath(), ex);
//        }
//        return model;
//    }
//
//    @Override
//    public <T> T update(T model) {
//        return null;
//    }
//
//    @Override
//    public <T> Iterable<T> findAll() {
//        return null;
//    }
//
//    @Override
//    public <T, ID> T findOne(ID id, Class<T> classType) {
//        Objects.requireNonNull(id, "ID must not be null");
//        Objects.requireNonNull(classType, "Class type must not be null");
//
//        if (!FILE.exists() || FILE.length() == 0) {
//            System.err.println("File does not exists");
//        }
//        Type type = TypeToken.getParameterized(List.class, classType).getType();
//
//        try (Reader reader = Files.newBufferedReader(FILE.toPath(), StandardCharsets.UTF_8)) {
//            List<T> values = gson.fromJson(reader, type);
//            if (values == null) {
//                log.error("There is no data in file:");
//            }
//
//            assert values != null;
//            return values.stream()
//                    .filter(item -> {
//                        try {
//                            Field idField = item.getClass().getField("id");
//                            idField.setAccessible(true);
//                            Object valueOfId = idField.get(item);
//
//                            return id.equals(valueOfId);
//                        } catch (NoSuchFieldException | IllegalAccessException ex) {
//                            log.error("Field 'id' not found or inaccessible in class {}", item.getClass().getName());
//                            return false;
//                        }
//                    }).findFirst()
//                    .orElse(null);
//
//        } catch (IOException | JsonSyntaxException ex) {
//            log.error("Failed to load JSON file during findOne: {}", FILE.getAbsolutePath(), ex);
//            throw new RuntimeException("Reading process failed: " + FILE.getAbsolutePath(), ex);
//        }
//    }