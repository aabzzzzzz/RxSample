package com.example.imitaterxava.core;

/**
 * 抽象被观察者. A
 * 被观察者的顶层接口. 提供订阅方法.
 * @param <T>
 */
public interface ObservableSource<T> {

    // addObserver
    void subscribe(Observer<T> observer);


}
