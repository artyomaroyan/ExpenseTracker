package main.java.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import main.java.model.Expense;
import main.java.model.ExpenseResponse;
import main.java.persistence.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 23:51:51
 */
public class ExpenseLoaderServiceImpl implements ExpenseLoaderService {
    private static final Logger log = LoggerFactory.getLogger(ExpenseLoaderServiceImpl.class);

    private final Type type = new TypeToken<List<Expense>>() {}.getType();
    private final File file = new File("Expense.json");
    private final Gson gson;

    private final ExpenseRepository<Expense, Long> expenseRepository;

    public ExpenseLoaderServiceImpl(ExpenseRepository<Expense, Long> expenseRepository) {
        this.expenseRepository = expenseRepository;
        this.gson = new Gson();
    }

    @Override
    public List<Expense> loadAll() {
        if (!file.exists() || file.length() == 0) {
            log.error("Failed to read file: File does not exists or is empty: {}", file.getAbsolutePath());
            throw new IllegalStateException("Can not read file: " + file.getAbsolutePath());
        }

        try (Reader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            List<Expense> expenses = gson.fromJson(reader, type);
            return expenses != null ? expenses : new ArrayList<>();

        } catch (IOException | JsonSyntaxException ex) {
            log.error("Failed to load JSON file: {}", file.getAbsolutePath(), ex);
            throw new RuntimeException("Reading process failed: " + file.getAbsolutePath(), ex);
        }
    }

    @Override
    public Optional<ExpenseResponse> loadById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<ExpenseResponse> loadByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Optional<ExpenseResponse> loadByAmount(double amount) {
        return Optional.empty();
    }

    @Override
    public Optional<ExpenseResponse> loadByCategory(String category) {
        return Optional.empty();
    }

    @Override
    public Optional<ExpenseResponse> loadByAmountAndCategory(double amount, String category) {
        return Optional.empty();
    }
}