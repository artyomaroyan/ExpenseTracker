package main.java.persistence;

/**
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 19:17:05
 */
public interface ModelMetadata<T> {
    Class<T> getJavaType();
}