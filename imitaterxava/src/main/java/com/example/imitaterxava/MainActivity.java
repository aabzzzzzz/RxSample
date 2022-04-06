package com.example.imitaterxava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.imitaterxava.core.Emitter;
import com.example.imitaterxava.core.Function;
import com.example.imitaterxava.core.Observable;
import com.example.imitaterxava.core.ObservableOnSubscribe;
import com.example.imitaterxava.core.Observer;
import com.example.imitaterxava.core.scheduler.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        testFOFOF();

        testAAA();


    }



    private void testAAA() {

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            Log.d("MXD", " " + "mxd" + " " + Thread.currentThread());
            emitter.onNext("mxd");
        }).subscribeOn(Schedulers.cachedPool())
        .observeOn(Schedulers.main())
        .map(s -> {
            Log.d("MXD", " " + s + " " + Thread.currentThread());
            return s + " map ";
        }).observeOn(Schedulers.cachedPool()).flatmap(s -> Observable.create((ObservableOnSubscribe<String>) emitter -> {
            Log.d("MXD", " " + s + " " + Thread.currentThread());
            emitter.onNext(s + " flatmap ");
        })).observeOn(Schedulers.main()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(String s) {
                Log.d("MXD", " " + s + " " + Thread.currentThread());
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });


    }


    private void testFOFOF() {
//        //具体要执行的类.
//        ObservableOnSubscribe<String> observableOnSubscribe = new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(Emitter<String> emitter) {
//                Log.d(" MAINIII ", "subscribe: " + Thread.currentThread());
//                System.out.println("subscribe: " + Thread.currentThread());
//                emitter.onNext("如你所见, 这是一个字符串.~~");
//            }
//        };
//
//        // 创建被观察者.
//        Observable<String> stringObservable = Observable.create(observableOnSubscribe)
//                .subscribeOn(Schedulers.main())
//                .observeOn(Schedulers.cachedPool());
//
//
//        Observable<String> map = stringObservable.map(new Function<String, String>() {
//            @Override
//            public String apply(String s) {
//                Log.d(" MAINIII ", "map: " + Thread.currentThread() + " -- s " + s);
//                return "map --- " + s + Thread.currentThread();
//            }
//        }).observeOn(Schedulers.cachedPool());
//
//        Observable<String> flatmap = map.flatmap(new Function<String, Observable<String>>() {
//            @Override
//            public Observable<String> apply(String s) {
//                return Observable.create(new ObservableOnSubscribe<String>() {
//                    @Override
//                    public void subscribe(Emitter<String> emitter) {
//                        Log.d(" MAINIII ", "flatmap: " + Thread.currentThread() + " -- " + s);
//                        emitter.onNext(s + " -- flatmap-- " + Thread.currentThread());
//                    }
//                });
//            }
//        }).observeOn(Schedulers.cachedPool());
//
//
//        // 观察者.
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onSubscribe() {
//                //被订阅了.
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.d(" MAINIII ", "flatmap: " + Thread.currentThread() + "  == s  -- " + s);
//                System.out.println("#onNext: " + Thread.currentThread() + " -- " + s);
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//            }
//        };
//
//        // 开始订阅.
//        flatmap.subscribe(observer);

    }


}