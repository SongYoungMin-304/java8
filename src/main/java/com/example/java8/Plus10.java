package com.example.java8;

import java.util.function.*;

public class Plus10 implements Function<Integer, Integer> {

    @Override
    public Integer apply(Integer integer) {
        return integer + 10;
    }

    public static void main(String[] args){
        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(10));

        //
        Function<Integer, Integer> plus11 = (i) ->{
            return i+10;
        };

        Function<Integer, Integer> plus12 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 10;
            }
        };

        System.out.println(plus11.apply(11));

        //
        Function<Integer, Integer> multiply2 = (i) ->{
            return i*2;
        };

        System.out.println(multiply2.apply(1));

        // multiply2 를 하고 plus11
        Function<Integer, Integer> compose = plus11.compose(multiply2);
        System.out.println(compose.apply(1));

        // plus11 를 하고 multiply2
        Function<Integer, Integer> integerIntegerFunction = plus11.andThen(multiply2);
        System.out.println(integerIntegerFunction.apply(2));


        // BIFunction(입력 값을 2개 받아서 1개 반환)
        // Comsumer(입력 값 1개만 받음)

        BiFunction<Integer, Integer, Integer> biFunction = (a, b) ->{
            return a + b;
        };

        System.out.println("테스트 영민"+ biFunction.apply(10, 20));

        Consumer<Integer> c = (i) -> System.out.println(i);
        c.accept(10);



        // Suppier(반환 값만 있음)

        // Preditable(T 타입을 받아서 boolean 을 리턴)

        // UnaryOperator 입력값과 결과 값의 타입이 같은 경우
        
        // BinaryOperaotr 입력값 2개 결과값 1개가 전부 같을 것이라고 봄


        Supplier<Integer> get10 = () -> 10;

        BinaryOperator<Integer> get20 = (a, b) -> a + b;



        System.out.println("test1"+get10.get());

        System.out.println("test2"+get20.apply(2, 4));



    }
}
