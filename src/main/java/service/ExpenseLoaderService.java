package main.java.service;

import main.java.model.Expense;
import main.java.model.ExpenseResponse;

import java.util.List;
import java.util.Optional;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 23:51:01
 */
public interface ExpenseLoaderService {
    List<Expense> loadAll();
    Optional<ExpenseResponse> loadById(Long id);
    Optional<ExpenseResponse> loadByTitle(String title);
    Optional<ExpenseResponse> loadByAmount(double amount);
    Optional<ExpenseResponse> loadByCategory(String category);
    Optional<ExpenseResponse> loadByAmountAndCategory(double amount, String category);
}