package main.java.ui;

import main.java.model.Category;
import main.java.model.Expense;
import main.java.service.ExpenseLoaderService;
import main.java.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

import static java.lang.IO.println;

/**
 * Author: Artyom Aroyan
 * Date: 23.06.26
 * Time: 15:38:49
 */
public class ConsoleUI {
    private static final Logger log = LoggerFactory.getLogger(ConsoleUI.class);

    private final UserInputHandler inputHandler;
    private final ExpenseService expenseService;
    private final ExpenseLoaderService loaderService;

    private boolean running = true;

    public ConsoleUI(UserInputHandler inputHandler, ExpenseService expenseService, ExpenseLoaderService loaderService) {
        this.inputHandler = inputHandler;
        this.expenseService = expenseService;
        this.loaderService = loaderService;
    }

    public void start() {
        // 1. print welcome
        welcome();

        while (running) {
            try {
                // 2. show main menu
                mainMenu();
                // 3. handle user input
                String choice = inputHandler.readLine();
                // 4. perform input action
                handleMainChoice(choice);
            } catch (Exception ex) {
                log.error("Unexpected error: {}", ex.getMessage());
                throw new IllegalStateException("Application Failed: ", ex.getCause());
            }
        }
        // 5. close input & stop app
        inputHandler.close();
        // 6. print goodbye
        goodBye();
    }

    private void handleMainChoice(String choice) {
        try {
            MainMenuOption option = MainMenuOption.fromString(choice);

            switch (option) {
                case ADD_EXPENSE -> addNewExpense();
                case UPDATE_EXPENSE -> updateExpense();
                case DELETE_EXPENSE -> deleteExpense();
                case FETCH_BY_ID -> fetchById();
                case FETCH_BY_TITLE -> fetchByTitle();
                case FETCH_BY_AMOUNT -> fetchByAmount();
                case FETCH_BY_CATEGORY -> fetchByCategory();
                case FETCH_BY_AMOUNT_AND_CATEGORY -> fetchByAmountAndCategory();
                case FETCH_ALL -> fetchAll();
                case EXIT -> running = false;
            }
        } catch (Exception ex) {
            log.error("Invalid option, Please try again. {}", ex.getMessage());
            throw new IllegalStateException("Option is invalid. Please choose valid Option from list.");
        }
    }

    private void fetchAll() {
        loaderService.loadAll();
    }

    private void fetchByAmountAndCategory() {

    }

    private void fetchByCategory() {

    }

    private void fetchByAmount() {

    }

    private void fetchByTitle() {

    }

    private void fetchById() {

    }

    private void deleteExpense() {

    }

    private void updateExpense() {
        println("Please follow commands...");
        println("Select field which you want to update (Title, Description, Amount, Category)");
        var selectedField = inputHandler.readLine();
        FieldsToUpdate fields = FieldsToUpdate.fromString(selectedField);

        switch (fields) {
            case TITLE -> updateTitle();
            case DESCRIPTION -> updateDescription();
            case AMOUNT -> updateAmount();
            case CATEGORY -> updateCategory();
        }
    }

    private void updateCategory() {

    }

    private void updateAmount() {

    }

    private void updateDescription() {

    }

    private void updateTitle() {
        println("please enter new title");
        var title = inputHandler.readTitle();
    }

    private void addNewExpense() {
        println("Please follow commands...");
        var title = inputHandler.readTitle();
        var description = inputHandler.readDescription();
        var amount = inputHandler.readAmount();
        var category = inputHandler.readCategory();
        expenseService.create(new Expense(
                null,
                title,
                description,
                amount,
                Category.valueOf(category),
                Instant.now()
        ));
    }

    private void mainMenu() {
        IO.println("\n" + "-".repeat(30));
        println("Main Menu");
        println("Add - Add new expense");
        println("Update - Update existing expense");
        println("Delete - Delete existing expense");
        println("Fetch by ID - Fetch expense with specified ID");
        println("Fetch by title - Fetch expense with specified title");
        println("Fetch by amount - Fetch expense with specified amount");
        println("Fetch by category - Fetch expense with specified category");
        println("Fetch by amount and category - Fetch expense with specified amount and category");
        println("Fetch all - Fetch all expenses");
        IO.println("\nChoose option: ");
    }

    private void welcome() {
        IO.println("\n" + "$".repeat(50));
        println("Welcome to Expense Tracker App.");
        IO.println("$".repeat(50));
    }

    private void goodBye() {
        IO.println("\nThank you for using Expense Tracker. Goodbye!");
    }
}