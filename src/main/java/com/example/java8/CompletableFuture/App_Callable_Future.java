package com.example.java8.CompletableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class App_Callable_Future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };

        Callable<String> YOUNGMIN = () -> {
            Thread.sleep(1000L);
            return "YOUNGMIN";
        };

        // invoke 다 기다린다...
        //List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, YOUNGMIN));
        String s = executorService.invokeAny(Arrays.asList(hello, java, YOUNGMIN));

        System.out.println(s);

        /*for (Future<String> future : futures) {
            System.out.println(future.get());
        }*/


        /*
        Future<String> helloFuture = executorService.submit(hello);

        System.out.println(helloFuture.isDone());
        System.out.println("Started!");


        //helloFuture.get(); // 블록킹
        helloFuture.cancel(false);

        System.out.println(helloFuture.isDone());

        System.out.println("End!");
        executorService.shutdown();*/
    }
}
