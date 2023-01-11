package com.example.java8;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

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
    }
}
