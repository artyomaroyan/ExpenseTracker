package main.java.persistence;

import java.lang.invoke.MethodHandles;
import java.util.Objects;
import java.util.function.Function;

/**
 * Reflection-based {@link ModelInformation} that resolves the identifier via
 * a field named {@code id} on the domain model class.
 *
 * <p>This implementation keeps the layer generic: it works for any domain
 * class that exposes its primary key through a no-arg method or a public/
 * record component named {@code id}. It uses {@link MethodHandles} instead
 * of raw {@link java.lang.reflect.Field} to avoid the fragile
 * {@code setAccessible(true)} pattern and to remain compatible with the
 * Java module system.
 *
 * <p>For compile-time safety, prefer supplying a {@code Function<T, ID>}
 * id-extractor (see constructor) rather than relying on reflection at all.
 *
 * @param <T>  the domain model type
 * @param <ID> the identifier type

 * Author: Artyom Aroyan
 * Date: 24.06.26
 * Time: 04:07:12
 */
public class ReflectiveModelInformation<T, ID> extends AbstractModelInformation<T, ID> {
    private final Class<ID> idType;
    private final Function<T, ID> idExtractor;

    /**
     * Creates an instance backed by an explicit id-extractor function.
     * This is the <em>preferred</em> constructor because it is fully
     * type-safe and requires no reflection at runtime.
     *
     * <pre>{@code
     * new ReflectiveModelInformation<>(Expense.class, Long.class, Expense::id)
     * }</pre>
     *
     * @param domainClass  the class of the domain model; must not be {@code null}
     * @param idType       the class of the identifier; must not be {@code null}
     * @param idExtractor  a function that extracts the id from a model instance
     */
    public ReflectiveModelInformation(Class<T> domainClass, Class<ID> idType, Function<T, ID> idExtractor) {
        super(domainClass);
        this.idType = Objects.requireNonNull(idType, "idType must not be null");
        this.idExtractor = Objects.requireNonNull(idExtractor, "idExtractor must not be null");
    }

    @Override
    public ID getId(T model) {
        Objects.requireNonNull(model, "Model must not be null");
        return idExtractor.apply(model);
    }

    @Override
    public Class<ID> getIdType() {
        return idType;
    }
}