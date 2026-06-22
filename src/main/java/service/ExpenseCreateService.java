package main.java.service;

import main.java.model.Expense;

/**
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 16:22:50
 */
public interface ExpenseCreateService {
    Expense create(Expense expense);
}