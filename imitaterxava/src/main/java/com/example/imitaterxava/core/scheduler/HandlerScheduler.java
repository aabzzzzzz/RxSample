package com.example.imitaterxava.core.scheduler;

import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HandlerScheduler extends Scheduler{

    private Handler handler;

    public HandlerScheduler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public Worker createWorker() {
        return new HandlerWorker(handler);
    }

    private static class HandlerWorker implements Worker{

        final Handler mapper;

        public HandlerWorker(Handler mapper) {
            this.mapper = mapper;
        }

        @Override
        public void schedule(Runnable command) {
            mapper.post(command);
        }
    }
}
