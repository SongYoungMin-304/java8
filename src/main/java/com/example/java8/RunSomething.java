package com.example.java8;

@FunctionalInterface
public interface RunSomething {
    
    // 메소드가 하나만 있으면 함수형 인터페이스
    int doIt(int number);

    // 인터페이스임에도 static 메소드 사용 가능
    static void pringName(){
        System.out.println("youngmin");
    }

    default void printAge(){
        System.out.println("30");
    }
}
