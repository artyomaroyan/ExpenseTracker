package main.java.service;

import main.java.model.Category;
import main.java.model.Expense;

import java.util.List;

/**
 * Author: Artyom Aroyan
 * Date: 06.04.26
 * Time: 17:51:10
 */
public interface ExpenseService {
    String createExpense(Expense expense);
    Expense getExpenseById(Long id);
    List<Expense> getExpenseByAmount(double amount);
    List<Expense> getExpenseByCategory(Category category);
    List<Expense> getExpenseByAmountAndCategory(double amount, Category category);
}