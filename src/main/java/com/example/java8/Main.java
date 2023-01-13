package com.example.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

public class Main {

    public static void main(String[] args) {
        Foo2 foo2 = new DefaultFoo("youngmin");
        foo2.printName();
        foo2.printNameUpperCase();


        System.out.println("----------------");

        //literable
        //Collection
        //Comparaotr

        List<String> name = new ArrayList<>();
        name.add("a");
        name.add("b");
        name.add("c");
        name.add("d");

        name.forEach(s ->{
            System.out.println();
        });

        name.forEach(System.out::println);

        System.out.println("-------------------");

        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator2 = spliterator.trySplit();
        while(spliterator.tryAdvance(System.out::println));

        System.out.println("------------------------");

        while(spliterator2.tryAdvance(System.out::println));

        long a = name.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("a"))
                .count();

        System.out.println(a);

        name.removeIf(s -> s.startsWith("b"));

        name.forEach(System.out::println);


        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        name.sort(compareToIgnoreCase.reversed());

        name.forEach(System.out::println);
    }
}
