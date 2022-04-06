package com.example.imitaterxava.core;

/**
 * function
 * @param <T>
 * @param <U>
 */
public interface Function<T, U> {
    U apply(T t);
}
