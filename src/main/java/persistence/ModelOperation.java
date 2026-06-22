package main.java.persistence;

/**
 * Author: Artyom Aroyan
 * Date: 22.06.26
 * Time: 21:54:13
 */
public interface ModelOperation {
    <T> T insert(T model);
    <T> T update(T model);
}