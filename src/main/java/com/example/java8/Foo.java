package com.example.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.*;

public class Foo {

    public static void main(String[] args){

       /// 익명 내부 클래스
        RunSomething2 runSomething = new RunSomething2() {
            @Override
            public void doIt() {
                System.out.println("Hello");
                System.out.println("Lamda");
            }
        };

        // 함수형 인터페이스(메소드 한개만 존재) lamda로 변경 가능
        RunSomething2 RunSomething2 = () -> {
            System.out.println("Hello");
            System.out.println("Lamda");
        };

        
        // 함수형 인터페이스(메소드 한개만 존재) lamda로 변경 가능
        RunSomething runSomething3 = (number) -> {
            return number+10;
        };


        System.out.println(runSomething3.doIt(10));

        // 변수 캡쳐
        Foo foo = new Foo();
        foo.run();
    }

    public void run() {

        // 로컬 변수 캡쳐가 됨  java8 이전에는 항상 final
        // 람다에서는 이 변수가 사실 상 final 인 경우 생략 가능..
        final int baseNumber = 10;

        // 로컬 클래스
        class  LocalClass {
            
            void prinBaseName(){
                int baseNumber = 11;
                System.out.println(baseNumber); // 11이 출력
            }
        }

        // 익명클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                int baseNumber = 11;
                System.out.println(baseNumber);
            }
        };

        // 람다
        IntConsumer printInt = (i) -> {
            //int baseNumber = 11; 같은 scope 이여서 안됨.
            System.out.println(i + baseNumber);
        };

        printInt.accept(10);

        System.out.println("==========================");



        Function<Integer, String> intToString = (i) -> "number";

        System.out.println(intToString.apply(10));


        Consumer<Integer> test1 = (s) -> {
            System.out.println(s);
        };

        Consumer<Integer> test2 = System.out::println;


        System.out.println("start");
        test1.accept(10);
        test2.accept(10);
        System.out.println("end");

        UnaryOperator<String> hi = (s) -> "hi "+s;
        // 전환

        UnaryOperator<String> hi2 = Greeting::hi;

        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;

        Supplier<Greeting> newGreeting = Greeting::new;

        Function<String, Greeting> newFunction
                = Greeting::new;

        System.out.println(newFunction.apply("song").getName());

        String [] names = {"song","young","min"};
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        Arrays.sort(names, String::compareToIgnoreCase);
    }
}
