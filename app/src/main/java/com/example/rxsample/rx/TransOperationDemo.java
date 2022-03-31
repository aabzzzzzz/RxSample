package com.example.rxsample.rx;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class TransOperationDemo {
    public static void main(String[] args) {

//        map();
//        flatMap();
//        concatMap();  和 flatMap() 基本上是一样的，只不过 concatMap() 转发出来的事件是有序的，而 flatMap() 是无序的。 不过flatmap 基本也是有序的.
//        buffer();





    }

    private static void flatMap() {

        Observable.just("模拟注册").flatMap(new Function<String, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(@NotNull String s) throws Exception {
                //模拟登录.
                return Observable.just(1010);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer o) throws Exception {
                System.out.println("aab " + o);
            }
        });

    }


    private static void map() {
        //可以将被观察者发送的数据类型转变成其他的类型
        Observable.just(1, 2, 3)
                .map(new Function< Integer, String >() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "I'm " + integer;
                    }
                })
                .subscribe(new Observer <String> () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("aab " + "onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("aab onNext" + s);
//                        Log.e(TAG, "===================onNext " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
