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


        // 중계 -> Stream 리턴한다.
        // 종료 - Stream x .collect 등...

        stringStream.forEach(System.out::println);

        System.out.println("-----------------");

        names.stream().map((s) ->{
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());

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
