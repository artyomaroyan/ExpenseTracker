package main.java.service;

import main.java.model.Expense;

import java.util.List;
import java.util.Optional;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 23:51:01
 */
public interface ExpenseLoaderService {
    List<Expense> loadAll();
    Optional<Expense> loadById(Long id);
    Optional<Expense> loadByTitle(String title);
    Optional<Expense> loadByAmount(double amount);
    Optional<Expense> loadByCategory(String category);
    Optional<Expense> loadByAmountAndCategory(double amount, String category);
}