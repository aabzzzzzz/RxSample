package com.example.imitaterxava.core;

// 提供Emitter的实例

/**
 * 用来绑定发射器用.
 * 被观察者与事件解耦的接口.
 * @param <T>
 */
public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter<T> emitter);
}
