package com.example.rxsample.rx;


import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class FilterOperationDemo {

    //过滤操作符.

    public static void main(String[] args) {

        Observable.range(1, 10).filter(new Predicate<Integer>() {
            @Override
            public boolean test(@NotNull Integer integer) throws Exception {
                if (integer > 5 && integer < 8){
                    return true;
                }
                return false;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("aab " + integer);
            }
        });


    }

}
