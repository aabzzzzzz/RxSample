package com.example.rxsample;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxLifecycle<T> implements LifecycleObserver, ObservableTransformer<T,T> {


    final CompositeDisposable compositeDisposable = new CompositeDisposable();


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(){
        Log.d("TAG", "onDestroy");

        if (!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }

    }

    @NotNull
    @Override
    public ObservableSource<T> apply(@NotNull Observable<T> upstream) {
        return upstream.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                compositeDisposable.add(disposable);
            }
        });
    }

    public static <T> RxLifecycle<T> bindRxlifecycle(LifecycleOwner lifecycleOwner) {
        RxLifecycle<T> lifecycle = new RxLifecycle<>();
        lifecycleOwner.getLifecycle().addObserver(lifecycle);
        return lifecycle;
    }


}
