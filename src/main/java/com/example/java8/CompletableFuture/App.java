package com.example.java8.CompletableFuture;

public class App {

    public static void main(String[] args) throws Exception{
        /*System.out.println(Thread.currentThread().getName());

        Mythread mythread = new Mythread();
        mythread.start();*/

        Thread thread = new Thread(() -> {
            /*try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            //System.out.println("Thread: " + Thread.currentThread().getName());

            /*while(true){
                System.out.println("Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("exit!");
                    return;
                }
            }*/
            System.out.println("Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        thread.start();



        // 기다리게 해준다... 스레드가 대기...

        thread.join();


        System.out.println("Hello: " + Thread.currentThread().getName());

        //Thread.sleep(3000L);
        //thread.interrupt();
    }

    static class Mythread extends Thread{
        @Override
        public void run(){
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }
}
