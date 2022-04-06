package com.example.imitaterxava.core.scheduler;


import java.util.concurrent.ExecutorService;

//
public class NewThreadScheduler extends Scheduler {
    private ExecutorService executorService;

    public NewThreadScheduler(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Worker createWorker() {
        return new NewThreadWorker(executorService);
    }

    private static class NewThreadWorker implements Worker {


        final ExecutorService mapper;

        public NewThreadWorker(ExecutorService executorService) {
            this.mapper = executorService;
        }

        @Override
        public void schedule(Runnable command) {
            mapper.execute(command);
        }
    }
}
