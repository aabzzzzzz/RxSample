package com.example.rxsample.rx;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class CreateOperationDemo {

    private static Observer<Integer> observer;

    public static void main(String[] args) {

        observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("aab " + "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("aab " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("aab " + e.toString());
            }

            @Override
            public void onComplete() {
                System.out.println("aab " + "onComplete");
            }
        };


//        create();
//        just();
//        fromArray(); 对数组做操作
//        fromIterable(); 对集合做操作(List)
//        fromFuture(); 参数中的 Future 是 java.util.concurrent 中的 Future，
//        Future 的作用是增加了 cancel() 等方法操作 Callable，它可以通过 get() 方法来获取 Callable 返回的值。
//        fromCallable();




    }

    private static void fromArray() {
        //突破10个限制.
        Integer[] arr = {1, 2, 3,1, 2, 3, 3,1, 2, 3, 3,1, 2, 3, 3,1, 2, 3, 3,1, 2, 3, 3,1, 2, 3, 3,1, 2, 3, 3,1, 2, 3, 3,1, 2, 3, 3,1, 2, 3, 3,1, 2, 3};
        Observable.fromArray(arr)
                .subscribe(observer);
    }

    private static void just() {
        // Just 操作符 很简单,
        Observable.just(1, 2, 3)
                .subscribe(observer);
    }

    private static void create() {

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello Observer");
                e.tryOnError(new Throwable("假消息..."));
                e.onComplete();
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("aab " + s);
//                Log.d("chan","=============onNext " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("aab " + "onComplete");
//                Log.d("chan","=============onComplete ");
            }
        };

//        observable.subscribe(observer);

        // Consumer 搭配lamda表达式 更好用....
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("aab " + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("aab " + throwable.toString());
            }
        });
    }

}
