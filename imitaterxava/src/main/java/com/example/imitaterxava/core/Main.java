package com.example.imitaterxava.core;

import com.example.imitaterxava.core.scheduler.Scheduler;
import com.example.imitaterxava.core.scheduler.Schedulers;

public class Main {


    public static void main(String[] args) {
        Main main = new Main();
//        main.testObserveOn();
        main.testFOFOF();
    }

    private void testFOFOF() {

        //具体要执行的类.
        ObservableOnSubscribe<String> observableOnSubscribe = new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Emitter<String> emitter) {
                System.out.println("subscribe: " + Thread.currentThread());
                emitter.onNext("如你所见, 这是一个字符串.~~");
            }
        };

        // 创建被观察者.
        Observable<String> stringObservable = Observable.create(observableOnSubscribe).subscribeOn(Schedulers.main());


        Observable<String> map = stringObservable.map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return "map --- " + s + Thread.currentThread();
            }
        });

        Observable<String> flatmap = map.flatmap(new Function<String, Observable<String>>() {
            @Override
            public Observable<String> apply(String s) {
                return Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(Emitter<String> emitter) {
                        emitter.onNext(s + " -- flatmap-- " + Thread.currentThread());
                    }
                });
            }
        });


        // 观察者.
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe() {
                //被订阅了.
            }

            @Override
            public void onNext(String s) {
                System.out.println("#onNext: " + Thread.currentThread() + " -- " + s);
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        };

        // 开始订阅.
        flatmap.subscribe(observer);

    }

    private void testMap() {
        Observable
                .create((ObservableOnSubscribe<String>)
                        emitter -> emitter.onNext("hello"))
                .map(s -> s + " world")
                .subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

    private void testSubscribeOn() {
        // 实现的subcribeOn
        // subscribeOn影响的是事件发射的线程（影响的是ObservableOnSubscribe的subscribe方法）
        System.out.println("TESTING subscribeOn -----------");
        Observable<String> create = Observable
                // 得看ObservableOnSubscribe#subscribe执行的线程
                .create(emitter -> {
                    System.out.println("ObservableOnSubscribe#subscribe: " + Thread.currentThread());
                    emitter.onNext("this is before the mapping function ");
                });

        create
                .subscribeOn(Schedulers.cachedPool())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe() {
                        // 这里onSubscribe也在子线程中执行，
                        System.out.println("onSubscribe: " + Thread.currentThread());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext: " + Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete: " + Thread.currentThread());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError: " + Thread.currentThread());
                    }
                });
    }

    private void testObserveOn() {
        System.out.println("TESTING subscribeOn -----------");
        Observable<String> create = Observable
                // 得看ObservableOnSubscribe#subscribe执行的线程
                .create(emitter -> {
                    System.out.println("ObservableOnSubscribe#subscribe: " + Thread.currentThread());
                    emitter.onNext("this is before the mapping function ");
                });

        create
                .subscribeOn(Schedulers.cachedPool())
                .observeOn(Schedulers.main())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe() {
                        // 这里onSubscribe也在子线程中执行，
                        System.out.println("onSubscribe: " + Thread.currentThread());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext: " + Thread.currentThread());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete: " + Thread.currentThread());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError: " + Thread.currentThread());
                    }
                });
    }

    private void testFlatmap() {
        Observable.create(
                (ObservableOnSubscribe<String>) emitter ->
                        emitter.onNext("12345"))
                .flatmap(s -> Observable.create((ObservableOnSubscribe<Integer>)
                                emitter ->
                        emitter.onNext(s.length())))
                .subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe() {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable throwable) {

            }
        });
    }

}
