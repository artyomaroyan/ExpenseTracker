package main.java.persistence;

import java.util.Objects;

/**
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 19:13:11
 */
public abstract class AbstractModelInformation<T, ID> implements ModelInformation<T, ID> {
    private final Class<T> domainClass;

    protected AbstractModelInformation(Class<T> domainClass) {
        Objects.requireNonNull(domainClass, "Domain class can not be null");
        this.domainClass = domainClass;
    }

    @Override
    public boolean isNew(T model) {
        ID id = getID(model);
        Class<ID> idType = getIdType();

        if (!idType.isPrimitive()) {
            return id == null;
        }

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