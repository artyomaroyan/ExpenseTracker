package main.java.persistence;

/**
 * Provides metadata and identity information about a domain model type.
 *
 * <p>This interface combines structural metadata (via {@link ModelMetadata})
 * with identity-resolution behavior, following the Information Expert principle:
 * the object that knows the structure of {@code T} is also the right place to
 * determine whether an instance of {@code T} is new or already persisted.
 *
 * @param <T>  the domain model type
 * @param <ID> the identifier type

 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 18:51:41
 */
public interface ModelInformation<T, ID> extends ModelMetadata<T> {

    /**
     * Returns {@code true} if the given model has never been persisted
     * (i.e. its identifier is absent or zero for primitives).
     *
     * @param model must not be {@code null}
     */
    boolean isNew(T model);

    /**
     * Extracts the identifier from the given model.
     *
     * @param model must not be {@code null}
     * @return the identifier value, or {@code null} if the model is new
     */
    ID getId(T model);

    /**
     * Returns the {@link Class} object representing the identifier type.
     */
    Class<ID> getIdType();
}