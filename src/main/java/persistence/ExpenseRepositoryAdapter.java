package main.java.persistence;

import main.java.model.Category;
import main.java.model.Expense;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 16:37:50
 */
public class ExpenseRepositoryAdapter implements ExpenseRepository<Expense, Long> {
    private final ModelInformation<Expense, Long> modelInformation;
    private final ModelOperation modelOperation;

    public ExpenseRepositoryAdapter(ModelInformation<Expense, Long> modelInformation, ModelOperation modelOperation) {
        this.modelInformation = modelInformation;
        this.modelOperation = modelOperation;
    }

    @Override
    public <S extends Expense> S save(S model) {
        Objects.requireNonNull(model, "Entity must not be null");

        if (modelInformation.isNew(model)) {
            return modelOperation.insert(model);
        } else {
            return modelOperation.update(model);
        }
    }

    @Override
    public Optional<Expense> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Optional<Expense> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Optional<Expense> findByAmount(double amount) {
        return Optional.empty();
    }

    @Override
    public Optional<Expense> findByCategory(Category category) {
        return Optional.empty();
    }

    @Override
    public Optional<Expense> findByAmountAndCategory(double amount, Category category) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Expense>> findAllExpenses() {
        return Optional.empty();
    }
}