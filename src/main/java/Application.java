package main.java;

import main.java.model.Category;
import main.java.model.Expense;
import main.java.persistence.*;
import main.java.service.ExpenseCreateService;
import main.java.service.ExpenseCreateServiceImpl;
import main.java.service.ExpenseLoaderService;
import main.java.service.ExpenseLoaderServiceImpl;
import main.java.validation.InputValidator;
import main.java.validation.InputValidatorImpl;

import java.time.Instant;

/**
 * Author: Artyom Aroyan
 * Date: 21.06.26
 * Time: 22:34:38
 */
public class Application {
    static void main() {
        ExpenseCreateService expenseCreateService = getExpenseCreateService();

        expenseCreateService.create(
                new Expense(
                        null,
                        "title1",
                        "description1",
                        12.2,
                        Category.valueOf("FOOD"),
                        Instant.now()
                ));
    }

    private static ExpenseCreateService getExpenseCreateService() {
        ExpenseLoaderService expenseLoaderService = new ExpenseLoaderServiceImpl();
        InputValidator inputValidator = new InputValidatorImpl(expenseLoaderService);
        ModelInformation<Expense, Long> modelInformation = new DelegatingModelInformation<>(new ModelInformationImpl());
        ModelOperation modelOperation = new ModelTemplate();
        ExpenseRepository<Expense, Long> repository = new ExpenseRepositoryAdapter(modelInformation, modelOperation);
        return new ExpenseCreateServiceImpl(repository);
    }
}


//        try (UserInputHandler input = new UserInputHandler(inputValidator)) {
//            expenseCreateService.create(
//                    new Expense(
//                            1L,
//                            "title1",
//                            "description1",
//                            12.2,
//                            Category.valueOf("FOOD"),
//                            Instant.now()
//                    ));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }