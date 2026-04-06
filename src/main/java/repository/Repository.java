package main.java.repository;

import main.java.model.Category;
import main.java.model.Expense;

import java.util.List;

/**
 * Author: Artyom Aroyan
 * Date: 06.04.26
 * Time: 17:50:51
 */
public interface Repository<T> {
    String save(T t);
    Expense findById(Long id);
    List<Expense> findByAmount(double amount);
    List<Expense> findByCategory(Category category);
    List<Expense> findByAmountAndCategory(double amount, Category category);
}