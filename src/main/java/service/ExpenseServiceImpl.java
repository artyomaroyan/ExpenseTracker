package main.java.service;

import main.java.model.Category;
import main.java.model.Expense;
import main.java.repository.Repository;

import java.util.List;

/**
 * Author: Artyom Aroyan
 * Date: 06.04.26
 * Time: 18:18:26
 */
public class ExpenseServiceImpl implements ExpenseService {
    private final Repository<Expense> repository;

    public ExpenseServiceImpl(Repository<Expense> repository) {
        this.repository = repository;
    }

    @Override
    public String createExpense(Expense expense) {
        return "";
    }

    @Override
    public Expense getExpenseById(Long id) {
        return null;
    }

    @Override
    public List<Expense> getExpenseByAmount(double amount) {
        return List.of();
    }

    @Override
    public List<Expense> getExpenseByCategory(Category category) {
        return List.of();
    }

    @Override
    public List<Expense> getExpenseByAmountAndCategory(double amount, Category category) {
        return List.of();
    }
}