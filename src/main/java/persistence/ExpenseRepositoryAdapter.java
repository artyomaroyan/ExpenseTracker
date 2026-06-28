package main.java.persistence;

import main.java.model.Category;
import main.java.model.Expense;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Adapts the generic {@link ModelOperations} infrastructure to the
 * domain-specific {@link ExpenseRepository} contract.
 *
 * <p>This is the Anti-Corruption Layer between the domain and the
 * persistence infrastructure. It delegates to {@link ModelInformation}
 * to decide whether a save is an insert or an update, keeping that
 * decision out of both the domain model and the storage layer.
 *
 * <p>Constructor injection is used throughout so that dependencies can
 * be replaced easily in tests without a DI framework.
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 16:37:50
 */
public class ExpenseRepositoryAdapter implements ExpenseRepository<Expense, Long> {
    private final ModelInformation<Expense, Long> modelInformation;
    private final ModelOperations<Expense, Long> modelOperations;

    public ExpenseRepositoryAdapter(ModelInformation<Expense, Long> modelInformation, ModelOperations<Expense, Long> modelOperations) {
        this.modelInformation = Objects.requireNonNull(modelInformation, "modelInformation must not be null");
        this.modelOperations = Objects.requireNonNull(modelOperations, "modelOperations must not be null");
    }

    /**
     * {@inheritDoc}
     *
     * <p>Delegates to {@link ModelOperations#insert} for new entities and
     * {@link ModelOperations#update} for existing ones. The newness check
     * is performed by {@link ModelInformation#isNew}.
     */
    @Override
    public Expense save(Expense model) {
        Objects.requireNonNull(model, "model must not be null");

        if (modelInformation.isNew(model)) {
            return modelOperations.insert(model);
        }
        return modelOperations.update(model);
    }

    @Override
    public Optional<Expense> findById(Long id) {
        Objects.requireNonNull(id, "id must not be null");

        return modelOperations.findById(id);
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
    public List<Expense> findAll() {
        return modelOperations.findAll();
    }

    @Override
    public void deleteById(Long id) {
        modelOperations.deleteById(id);
    }
}