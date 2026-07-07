package main.java.context;

import main.java.configuration.ApplicationConfiguration;
import main.java.model.Expense;
import main.java.persistence.*;
import main.java.service.ExpenseService;
import main.java.service.ExpenseServiceImpl;
import main.java.service.ExpenseLoaderService;
import main.java.service.ExpenseLoaderServiceImpl;
import main.java.ui.ConsoleUI;
import main.java.ui.UserInputHandler;
import main.java.validation.InputValidator;
import main.java.validation.InputValidatorImpl;

import java.nio.file.Path;
import java.util.Objects;

/**
 *  Manual dependency-injection root for the application.
 *
 *  <p>This record owns the construction and wiring of every major component.
 *  Having a single place that assembles the object graph means:
 *  <ul>
 *    <li>dependencies are explicit and traceable,</li>
 *    <li>switching an implementation (e.g. file → database) requires
 *        a change in exactly one place,</li>
 *    <li>tests can call {@code initialize} with a test configuration and
 *        get a fully wired context without a DI framework.</li>
 *  </ul>
 *
 *  <h2>What was fixed from the original</h2>
 *  <ul>
 *    <li>Removed raw type {@code ModelOperations} — now fully typed as
 *        {@code ModelOperations<Expense, Long>}.</li>
 *    <li>Removed {@code DelegatingModelInformation} (pure boilerplate)
 *        and {@code ModelInformationImpl} (too concrete) — replaced with
 *        the generic {@link ReflectiveModelInformation} and a method
 *        reference id-extractor.</li>
 *    <li>{@code ModelTemplate} now receives the file path and model class
 *        it needs rather than reading them from a static global.</li>
 *    <li>{@code getExpenseFilePath()} returns {@link Path} directly,
 *        so no conversion is needed here.</li>
 *    <li>{@code modelInformation} and {@code modelOperations} are no longer
 *        exposed on the record — they are infrastructure details that no
 *        external caller should depend on; only the repository is public.</li>
 *  </ul>
 *
 *  @param consoleUI         the top-level UI component
 *  @param inputValidator    validates raw user input
 *  @param expenseService    handles expense creation and mutation
 *  @param loaderService     handles expense retrieval and display
 *  @param expenseRepository the domain repository (intentionally the only
 *                           persistence type visible outside this class)

 * Author: Artyom Aroyan
 * Date: 23.06.26
 * Time: 15:34:06
 */
public record ApplicationContext(
        ConsoleUI consoleUI, InputValidator inputValidator,
        ExpenseService expenseService, ExpenseLoaderService loaderService,
        ExpenseRepository<Expense, Long> expenseRepository
) {

    /**
     * Builds and wires the complete application context from the given
     * configuration.
     *
     * @param configuration must not be {@code null}
     * @return a fully initialised {@link ApplicationContext}
     */
    public static ApplicationContext initialize(ApplicationConfiguration configuration) {
        Objects.requireNonNull(configuration, "configuration must not be null");

        ModelInformation<Expense, Long> modelInfo = new ReflectiveModelInformation<>(Expense.class, Long.class, Expense::id);
        ModelOperations<Expense, Long> operations = new ModelTemplate<>(configuration.getExpenseFilePath(), Expense.class, modelInfo);
        ExpenseRepository<Expense, Long> repository = new ExpenseRepositoryAdapter(modelInfo, operations);
        ExpenseLoaderService loader = new ExpenseLoaderServiceImpl(repository);
        InputValidator validator = new InputValidatorImpl();
        ExpenseService service = new ExpenseServiceImpl(validator, repository);
        ConsoleUI console = new ConsoleUI(new UserInputHandler(), service, loader);
        return new ApplicationContext(console, validator, service, loader, repository);
    }
}