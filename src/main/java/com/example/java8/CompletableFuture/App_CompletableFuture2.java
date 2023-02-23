package com.example.java8.CompletableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class App_CompletableFuture2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World" + Thread.currentThread().getName());
            return "World";
        });

/*        hello.get();
        world.get();*/

        CompletableFuture<String> future = hello.thenCompose(App_CompletableFuture2::getWorld);

        CompletableFuture<String> fu = hello.thenCombine(world, (h, w) -> {
            return h + " " + w;
        });

        System.out.println(fu.get());
    }



    private static CompletableFuture<String> getWorld(String message){
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(message + Thread.currentThread().getName());
            return message + "World";
        });
    }
}
