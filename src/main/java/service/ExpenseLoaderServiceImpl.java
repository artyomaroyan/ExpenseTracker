package main.java.service;

import main.java.model.Category;
import main.java.model.Expense;
import main.java.persistence.ExpenseRepository;

import java.util.List;
import java.util.Optional;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 23:51:51
 */
public class ExpenseLoaderServiceImpl implements ExpenseLoaderService {
    private final ExpenseRepository<Expense, Long> expenseRepository;

    public ExpenseLoaderServiceImpl(ExpenseRepository<Expense, Long> expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> loadAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Optional<Expense> loadById(Long id) {
        return expenseRepository.findById(id);
    }

    @Override
    public Optional<Expense> loadByTitle(String title) {
        return expenseRepository.findByTitle(title);
    }

    @Override
    public Optional<Expense> loadByAmount(double amount) {
        return expenseRepository.findByAmount(amount);
    }

    @Override
    public Optional<Expense> loadByCategory(String category) {
        return expenseRepository.findByCategory(Category.valueOf(category));
    }

    @Override
    public Optional<Expense> loadByAmountAndCategory(double amount, String category) {
        return expenseRepository.findByAmountAndCategory(amount, Category.valueOf(category));
    }
}
