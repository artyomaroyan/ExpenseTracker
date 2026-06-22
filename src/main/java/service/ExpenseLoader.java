package main.java.service;

import main.java.model.Expense;

import java.util.List;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 23:51:01
 */
public interface ExpenseLoader {
    List<Expense> loadAll();
}