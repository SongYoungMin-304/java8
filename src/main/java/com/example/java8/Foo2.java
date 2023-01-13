package com.example.java8;

public interface Foo2 {

    void printName();

    default void printNameUpperCase(){
        System.out.println(getName().toUpperCase());
    }

    String getName();
}
