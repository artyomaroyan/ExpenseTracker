package main.java.service;

import main.java.model.Expense;
import main.java.persistence.ExpenseRepository;

/**
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 21:48:51
 */
public class ExpenseCreateServiceImpl implements ExpenseCreateService {
    private final ExpenseRepository<Expense, Long> repository;

    public ExpenseCreateServiceImpl(ExpenseRepository<Expense, Long> repository) {
        this.repository = repository;
    }

    @Override
    public Expense create(Expense expense) {
        return repository.save(expense);
    }
}