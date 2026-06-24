package main.java.persistence;

import java.util.Objects;

/**
 * Base implementation of {@link ModelInformation} that derives the
 * {@code isNew} decision from the identifier value alone.
 *
 * <p>Subclasses supply the domain-specific {@link #getId} and
 * {@link #getIdType} implementations; all other behavior is inherited.
 *
 * <p><b>Newness rules</b>
 * <ul>
 *   <li>Reference types (e.g. {@link Long}): new when the id is {@code null}.</li>
 *   <li>Numeric primitives / their wrappers: new when the value equals {@code 0}.</li>
 * </ul>
 *
 * @param <T>  the domain model type
 * @param <ID> the identifier type

 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 19:13:11
 */
public abstract class AbstractModelInformation<T, ID> implements ModelInformation<T, ID> {
    private final Class<T> domainClass;

    protected AbstractModelInformation(Class<T> domainClass) {
        this.domainClass = Objects.requireNonNull(domainClass, "Domain class can not be null");
    }

    /**
     * {@inheritDoc}
     *
     * <p>Returns {@code true} when:
     * <ul>
     *   <li>the ID type is a reference type and the ID is {@code null}, or</li>
     *   <li>the ID type is a {@link Number} subtype and the long value is {@code 0}.</li>
     * </ul>
     *
     * @throws IllegalArgumentException for primitive ID types that are not numeric
     */
    @Override
    public boolean isNew(T model) {
        Objects.requireNonNull(model, "Model must not be null");
        ID id = getId(model);
        Class<ID> idType = getIdType();

        // Reference type: null  → new entity
        if (!idType.isPrimitive()) {
            return id == null;
        }

        // Primitive numeric type: zero-value → new entity
        if (id instanceof Number n) {
            return n.longValue() == 0L;
        }
        throw new IllegalArgumentException(String.format("Unsupported primitive ID type %s", idType));
    }

    @Override
    public Class<T> getJavaType() {
        return this.domainClass;
    }
}