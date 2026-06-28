package main.java.persistence;

import java.util.List;
import java.util.Optional;

/**
 * Low-level persistence operations that abstract the underlying storage
 * mechanism (file, database, in-memory, …) from the rest of the application.
 *
 * <p>This interface is intentionally narrow: it exposes only the operations
 * needed by the adapter layer. Higher-level query methods belong on the
 * domain-specific repository interface (e.g. {@code ExpenseRepository}).
 *
 * <p>All implementations must be thread-safe or clearly document their
 * threading assumptions.
 *
 * @param <T>  the domain model type this template operates on
 * @param <ID> the identifier type of the domain model

 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 21:54:13
 */
public interface ModelOperations<T, ID> {

    /**
     * Persists a new model and returns the saved state (which may include
     * a generated identifier).
     *
     * @param model must not be {@code null}
     * @return the persisted model; never {@code null}
     */
    T insert(T model);

    /**
     * Overwrites an existing model and returns the saved state.
     *
     * @param model must not be {@code null}; must already exist in the store
     * @return the updated model; never {@code null}
     * @throws java.util.NoSuchElementException if no record with the same id exists
     */
    T update(T model);

    /**
     * Returns all persisted instances.
     *
     * @return a non-null, possibly empty {@link List}
     */
    List<T> findAll();

    /**
     * Returns the model identified by {@code id}, or {@link Optional#empty()}
     * when no such record exists.
     *
     * @param id must not be {@code null}
     * @return an {@link Optional} wrapping the found model
     */
    Optional<T> findById(ID id);

    /**
     *
     *
     * @param id must not be {@code null}
     */
    void deleteById(ID id);
}