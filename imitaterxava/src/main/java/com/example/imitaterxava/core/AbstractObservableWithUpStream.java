package com.example.imitaterxava.core;

/**
 * 装饰器模式.
 * @param <T>
 * @param <U>
 */
public abstract class AbstractObservableWithUpStream<T, U> extends Observable<U> {
    final ObservableSource<T> source;

    public AbstractObservableWithUpStream(ObservableSource<T> source) {
        this.source = source;
    }
}
