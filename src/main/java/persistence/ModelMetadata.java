package main.java.persistence;

/**
 * Carries basic structural metadata about a domain model type.
 *
 * <p>Kept as a separate interface so that components that only need
 * type information (e.g. serialization helpers) do not have to depend
 * on the full {@link ModelInformation} contract.
 *
 * @param <T> the domain model type

 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 19:17:05
 */
public interface ModelMetadata<T> {

    /**
     * Returns the {@link Class} object representing the domain model type.
     */
    Class<T> getJavaType();
}