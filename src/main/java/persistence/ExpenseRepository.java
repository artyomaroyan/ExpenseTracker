package main.java.persistence;

import main.java.model.Category;

import java.util.List;
import java.util.Optional;

/**
 * Repository abstraction for Expense persistence operations.
 *
 * <p>Follows the Repository pattern from Domain-Driven Design. The generic
 * parameters are kept here to allow reuse across different aggregate types
 * if the project grows, while still providing a typed contract at the
 * call-site via {@link ExpenseRepositoryAdapter}.
 *
 * @param <T>  the domain model type
 * @param <ID> the identifier type of the domain model

 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 16:27:45
 */
public interface ExpenseRepository<T, ID> {

    /**
     * Saves (inserts or updates) the given model.
     *
     * @param model must not be {@code null}
     * @return the saved model; never {@code null}
     * @throws IllegalArgumentException if {@code model} is {@code null}
     */
    T save(T model);

    /**
     * Returns the model with the given identifier, or {@link Optional#empty()}
     * if none found.
     *
     * @param id must not be {@code null}
     * @return an {@link Optional} wrapping the found model
     */
    Optional<T> findById(ID id);

    /**
     * Returns the model with the given title, or {@link Optional#empty()}
     * if none found
     *
     * @param title must not be {@code null}
     * @return an {@link Optional} wrapping the found model
     */
    Optional<T> findByTitle(String title);

    /**
     * Returns the model with the given amount, or {@link Optional#empty()}
     * if none found
     *
     * @param amount must not be {@code null}
     * @return an {@link Optional} wrapping the found model
     */
    Optional<T> findByAmount(double amount);

    /**
     * Returns the model with the given category, or {@link Optional#empty()}
     * if none found
     *
     * @param category must not be {@code null}
     * @return an {@link Optional} wrapping the found model
     */
    Optional<T> findByCategory(Category category);

    /**
     * Returns the model with the given amount and category, or {@link Optional#empty()}
     * if none found
     *
     * @param amount   must not be {@code null}
     * @param category must not be {@code null}
     * @return an {@link Optional} wrapping the found model
     */
    Optional<T> findByAmountAndCategory(double amount, Category category);

    /**
     * Returns all persisted instances of type {@code T}.
     *
     * @return a non-null, possibly empty {@link List}
     */
    List<T> findAll();

    /**
     * Delete a model with specified id
     *
     * @param id must not be {@code null}
     */
    void deleteById(ID id);
}