package com.example.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");

        Stream<String> stringStream = names.stream()
                .map(String::toUpperCase);

        Stream<String> stringStream2 = names.stream()
                .map(n -> n.toUpperCase());

        // 중계 -> Stream 리턴한다.
        // 종료 - Stream x .collect 등...

        System.out.println("start--");
        stringStream.forEach(System.out::println);

        stringStream2.forEach(s -> System.out.println(s));

        System.out.println("end--");


        System.out.println("-----------------");


        List<String> collect1 = names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());

        Stream<String> stringStream1 = names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        });

        System.out.println("*************************");

/*
        collect1.forEach(s -> System.out.println(s));

        stringStream1.forEach(s -> System.out.println(s));
*/


        for (String name : names) {
            if(name.startsWith("k")){
                System.out.println(name.toUpperCase());
            }
        }

        List<String> collect = names.parallelStream().map((s) ->{
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());

        collect.forEach(System.out::println);
    }
}
