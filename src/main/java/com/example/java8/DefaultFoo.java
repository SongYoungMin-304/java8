package com.example.java8;

public class DefaultFoo implements Foo2 {

    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printName() {

    }

    @Override
    public String getName() {
        return this.name;
    }
}
