package main.java.service;

import main.java.model.Expense;
import main.java.model.ExpenseResponse;
import main.java.persistence.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 23:51:51
 */
public class ExpenseLoaderServiceImpl implements ExpenseLoaderService {
    private static final Logger log = LoggerFactory.getLogger(ExpenseLoaderServiceImpl.class);

    private final ExpenseRepository<Expense, Long> expenseRepository;

    public ExpenseLoaderServiceImpl(ExpenseRepository<Expense, Long> expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<Expense> loadAll() {
        return expenseRepository.findAll();
    }

    @Override
    public Optional<ExpenseResponse> loadById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<ExpenseResponse> loadByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Optional<ExpenseResponse> loadByAmount(double amount) {
        return Optional.empty();
    }

    @Override
    public Optional<ExpenseResponse> loadByCategory(String category) {
        return Optional.empty();
    }

    @Override
    public Optional<ExpenseResponse> loadByAmountAndCategory(double amount, String category) {
        return Optional.empty();
    }
}