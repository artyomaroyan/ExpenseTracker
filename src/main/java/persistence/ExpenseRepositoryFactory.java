//package main.java.persistence;
//
//import main.java.model.Expense;
//
//import java.nio.file.Path;
//
//**
// * Author: Artyom Aroyan
// * Date: 24.06.26
// * Time: 05:03:19
// */
//public class ExpenseRepositoryFactory {
//
//    private ExpenseRepositoryFactory() {
//    }
//
//    public static ExpenseRepository<Expense, Long> create(Path dataFilePath) {
//        // 1. ModelInformation: knows how to identify an Expense
//        //    — type-safe lambda replaces the old reflection-based approach
//        ModelInformation<Expense, Long> modelInfo = new ReflectiveModelInformation<>(Expense.class, Long.class, Expense::id);
//
//        // 2. ModelOperations: handles low-level file I/O
//        ModelOperations<Expense, Long> operations = new ModelTemplate<>(dataFilePath, Expense.class, modelInfo);
//
//        // 3. Repository adapter: ties domain contract to infrastructure
//        return new ExpenseRepositoryAdapter(modelInfo, operations);
//    }
//}