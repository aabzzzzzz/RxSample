package com.example.imitaterxava.core;


import com.example.imitaterxava.core.scheduler.Scheduler;

/**
 * 被观察者.
 * 被观察者抽象类, 并提供实际订阅的抽象方法.
 * @param <T>
 */
public abstract class Observable<T> implements ObservableSource<T>{
    @Override
    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }

    abstract void subscribeActual(Observer<T> observer);

    /**
     * 创建一个被观察者.
     * @param source
     * @param <T>
     * @return
     */
    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        // source -> ObservableOnSubscribe
        return new ObservableCreate<>(source);
    }

    public <U> Observable<U> map(Function<T, U> function) {
        return new ObservableMap<>(this, function);
    }

    public <U> Observable<U> flatmap(Function<T, Observable<U>> function) {
        return new ObservableFlatmap<>(this, function);
    }

    public Observable<T> subscribeOn(Scheduler scheduler) {
        return new ObservableSubscribeOn<>(this, scheduler);
    }

    public Observable<T> observeOn(Scheduler scheduler) {
        return new ObservableObserveOn<>(this, scheduler);
    }
}
