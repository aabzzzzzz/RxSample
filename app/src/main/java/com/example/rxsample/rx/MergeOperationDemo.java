package com.example.rxsample.rx;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MergeOperationDemo {

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


        concat();
        //concatArray(); 类似于 just和 fromArray
        //merge(); merge 是用来并行发送事件. concat是串行.

    }

    private static void concat() {
        //组合操作符,
        Observable.concat(Observable.just("111"), Observable.just("222")).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("aab s" + s);
            }
        });

    }

}
