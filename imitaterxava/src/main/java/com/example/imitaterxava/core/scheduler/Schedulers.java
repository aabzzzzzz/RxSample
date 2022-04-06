package com.example.imitaterxava.core.scheduler;


import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executors;

// 提供单例
public class Schedulers {

//    private static Scheduler cachedPool = null;
//    private static Scheduler main = null;

    private static Scheduler MAIN_THREAD = null;
    private static Scheduler NEW_THREAD = null;


    static {
        MAIN_THREAD = new HandlerScheduler(new Handler(Looper.getMainLooper()));
        NEW_THREAD = new NewThreadScheduler(Executors.newScheduledThreadPool(2));
    }

    public static Scheduler main() { return MAIN_THREAD; }
    public static Scheduler cachedPool() {
        return NEW_THREAD;
    }
}
