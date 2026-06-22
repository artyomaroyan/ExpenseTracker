package main.java.persistence;

/**
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 18:51:41
 */
public interface ModelInformation<T, ID> extends ModelMetadata<T> {
    boolean isNew(T model);
    ID getID(T model);
    Class<ID> getIdType();
}