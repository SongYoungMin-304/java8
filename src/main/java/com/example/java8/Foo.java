package com.example.java8;

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
    }
}
