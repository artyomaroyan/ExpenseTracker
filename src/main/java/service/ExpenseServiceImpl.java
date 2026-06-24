package main.java.service;

import main.java.model.Category;
import main.java.model.Expense;
import main.java.model.ExpenseRequest;
import main.java.model.ExpenseResponse;
import main.java.persistence.ExpenseRepository;

import java.time.Instant;

/**
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 21:48:51
 */
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository<Expense, Long> repository;

    public ExpenseServiceImpl(ExpenseRepository<Expense, Long> repository) {
        this.repository = repository;
    }

    @Override
    public Expense create(Expense expense) {
        return repository.save(expense);
    }

    @Override
    public ExpenseResponse update(ExpenseRequest request) {
        var mapped = mapRequestToModel(request);
        var saved = repository.save(mapped);
        return mapModelToResponse(saved);
    }

    private Expense mapRequestToModel(ExpenseRequest request) {
        return new Expense(
                null,
                request.title(),
                request.description(),
                request.amount(),
                Category.valueOf(request.category()),
                Instant.now()
        );
    }

    private ExpenseResponse mapModelToResponse(Expense expense) {
        return new ExpenseResponse(expense.id(), expense.title());
    }
}