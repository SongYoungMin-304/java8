package com.example.java8.CompletableFuture;

import java.util.Locale;
import java.util.concurrent.*;

public class App_CompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> future = executorService.submit(() -> "hello");

        future.get();*/

        CompletableFuture<Object> future = new CompletableFuture<>();
        future.complete("youngmin");
        System.out.println(future.get());

        CompletableFuture<String> future2 = CompletableFuture.completedFuture("youngmin");
        System.out.println(future2.get());

        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
        });
        future3.get();

        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> {
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });

        System.out.println(future4.get());





    }
}
