package com.example.rxsample.rx;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ToolOperationDemo {


    public static void main(String[] args) {


        // subscribeOn  指定被观察者的线程，要注意的时，如果多次调用此方法，只有第一次有效。也就是说 位于链式调用的最上层才有用.

        // observeOn  指定观察者的线程，每指定一次就会生效一次。(也就是说, 有大量的链式调用的话, 可以通过这个方法 来切换线程)

        //

        Observable.concat(Observable.just("111"), Observable.just("222"))
                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.mainThread()) 主线程.
                .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("aab s" + s);
            }
        });

    }

}
