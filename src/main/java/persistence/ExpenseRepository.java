package main.java.persistence;

import main.java.model.Category;

import java.util.List;
import java.util.Optional;

/**
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 16:27:45
 */
public interface ExpenseRepository<T, ID> {
    <S extends T> S save(S model);
    Optional<T> findById(ID id);
    Optional<T> findByTitle(String title);
    Optional<T> findByAmount(double amount);
    Optional<T> findByCategory(Category category);
    Optional<T> findByAmountAndCategory(double amount, Category category);
    Optional<List<T>> findAllExpenses();
}