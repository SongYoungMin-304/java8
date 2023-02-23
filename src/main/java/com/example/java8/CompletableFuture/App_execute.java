package com.example.java8.CompletableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App_execute {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(getRunnable("Hello1"));
        executorService.submit(getRunnable("Hello2"));
        executorService.submit(getRunnable("Hello3"));
        executorService.submit(getRunnable("Hello4"));
        executorService.submit(getRunnable("Hello5"));
        executorService.shutdown();

        ScheduledExecutorService executorService1 = Executors.newSingleThreadScheduledExecutor();
        executorService1.scheduleAtFixedRate(getRunnable("hELLO"),1,2, TimeUnit.SECONDS);

        //executorService.shutdownNow();

    }

    private static Runnable getRunnable(String message) {
        return () -> {
            System.out.println(message + Thread.currentThread().getName());
        };
    }
}
