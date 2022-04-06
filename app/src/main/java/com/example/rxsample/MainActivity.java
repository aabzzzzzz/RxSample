package com.example.rxsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView viewById;
    private Disposable subscribe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewById = findViewById(R.id.tv_alalala);

//
        RxLifecycle rxLifecycle = new RxLifecycle();
        getLifecycle().addObserver(rxLifecycle);


        RxBus.get().toObservable(String.class).compose(RxLifecycle.bindRxlifecycle(this)).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d("TAG", "H----ello -- " + s);
            }
        });


//
        subscribe = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NotNull ObservableEmitter<String> emitter) throws Exception {
                Log.d("TAG", "H----ello");
                Thread.sleep(5000);
                emitter.onNext("Hello");
            }
        })
                .compose(RxSchedulers.schedulersTransformer)
                .compose(RxLifecycle.bindRxlifecycle(this))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("TAG", s);
                        viewById.setText(s);
                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
                    }
                });

//        test();




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        subscribe.dispose();
        Log.d("TAG", "onDestroy");
    }

    //
//    public static void test() {
//        // 编辑部 编辑好的文案内容
//        String msg = "重大消息：以后Android程序员必须要学习Kotlin，必须要学习各种新技术与跨平台的内容啦";
//
//        // 创建一个微信公众号服务（被观察者）
//        Observable server = new WechatServerObservable();
//
//        // 创建 用户 （观察者）  多个
//        Observer shizhenxiangUser = new UserPerson("史帧湘");
//        Observer duzitenUser = new UserPerson("杜子腾");
//        Observer daiyuejinUser = new UserPerson("戴悦京");
//        Observer liumangUser = new UserPerson("刘芒");
//
//        // 订阅  注意：这里的订阅还是 被观察者.订阅(观察者)  关注
//        server.addObserver(shizhenxiangUser);
//        server.addObserver(duzitenUser);
//        server.addObserver(daiyuejinUser);
//        server.addObserver(liumangUser);
//
//        // 微信平台 发生了 改变
//         server.pushMessage(msg);
//        // 肚子疼 取消了关注
//        System.out.println("============================================");
//        server.removeObserver(duzitenUser);
//
//        // 再次微信平台 发生了 改变
//        server.pushMessage(msg);
//
//        // 传统的观察者模式, 是1 对 多的 关系.
//
//
//
//    }

}