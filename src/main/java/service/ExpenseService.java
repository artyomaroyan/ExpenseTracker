package main.java.service;

import main.java.model.Expense;
import main.java.model.ExpenseRequest;
import main.java.model.ExpenseResponse;

/**
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 16:22:50
 */
public interface ExpenseService {
    Expense create(Expense expense);
    ExpenseResponse update(ExpenseRequest request);
}