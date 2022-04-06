package com.example.imitaterxava.core;

/**
 * 发射器, 用来解耦 被观察者.
 *
 * 事件发射器接口, 提供发送事件的方法
 *
 * @param <T>
 */
public interface Emitter<T> {

    void onNext(T t);

    void onComplete();

    void onError(Throwable throwable);

}
