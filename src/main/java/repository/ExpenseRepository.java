package repository;

import model.Category;
import model.Expense;

import java.util.List;

/**
 * Author: Artyom Aroyan
 * Date: 06.04.26
 * Time: 18:03:28
 */
public class ExpenseRepository implements Repository<Expense> {

    @Override
    public String save(Expense expense) {
        return "";
    }

    @Override
    public Expense findById(Long id) {
        return null;
    }

    @Override
    public List<Expense> findByAmount(double amount) {
        return List.of();
    }

    @Override
    public List<Expense> findByCategory(Category category) {
        return List.of();
    }

    @Override
    public List<Expense> findByAmountAndCategory(double amount, Category category) {
        return List.of();
    }
}