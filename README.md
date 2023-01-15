# java8

# 2023.01.09

### 1. 함수형 인터페이스

```java
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

package com.example.java8;

public interface RunSomething2 {

    public void doIt();
}
```

→ @FunctionalInterface 가 있으면 함수형 인터페이스라는 뜻으로 메소드가 여러 개 생기면 컴파일 에러 

```java
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
```

→ 기존에는 익명 내부 클래스 사용 

→ 함수형 인터페이스의 경우 람다식 사용 가능


### 2. 기본적인 함수형 인터페이스

1) Function<Integer, Integer>→ 입력값, 반환값 전부 Interger

```java
// 람다 방식
Function<Intger, Integer> plus10 = (i) -> {
     return i + 10;
};

plus.apply(1);
// 결과 값 11

// 익명 함수
Function<Integer, Integer> plus10_v2 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 10;
            }
        };

Function<Integer, Intger> multiply2 = (i) -> {
     return i * 2;
}

Function<Integer, Integer> intergration = plus10.andThen(multiply2);

integration.apply(1)
// 결과 값 22
```

2) BIFunction

→ 입력값 두개를 받아서 1개 반환

```java
BIFunction<Integer, Integer, Integer> bifunction = (a, b) -> {
     return a + b;
}

bifunction.apply(10, 20);
// 결과 값 30
```

3) Consumser

→ 입력 값 1개만 받음

```java
Consumer<Integer> c = (i) -> System.out.println(i);
c.accept(10);

// 익명함수 방식
Consumer<Integer> c2 = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };

```

### 3. 람다식의 SCOPE 관련 내용

→ 람다식은 전역변수와 같은 SCOPE를 가지고 있어서 전역변수명과 같은 지역 변수 사용이 불가하다.(지역 변수 개념 존재 X)

```java
// 익명클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                int baseNumber = 11;
                System.out.println(baseNumber);
            }
        };

        // 람다
        Consumer<Integer> printInt = (i) -> {
            //int baseNumber = 11; 같은 scope 이여서 안됨.
            System.out.println(i + baseNumber);
        };
```

### 4. 메소드 레퍼런스

→ 람다식에서 메소드 레퍼런스로 좀 더 간단하게 표현 가능

```java
// 람다식으로 출력
Consumer<Integer> test1 = (s) -> {
            System.out.println(s);
        };

// 람다식을 메소드 레퍼런스로 변경
//(s) -> { System.out.println(s); } 
//System.out::println
Consumer<Integer> test2 = System.out::println;

System.out.println("start");
test1.accept(10);
test2.accept(10);
System.out.println("end");

//UnaryOperator<T> 
//<T> 형을 받아서 <T> 형으로 반환
UnaryOperator<String> hi = (s) -> "hi" +s;

UnaryOperator<String> hi2 = Greeting::hi;

public static String hi(String name){
     return "hi" + name;
}
```

### 5. 인터페이스 default 메소드

```java
package com.example.java8;

public interface Foo2 {

    void printName();

    default void printNameUpperCase(){
        System.out.println(getName().toUpperCase());
    }

    String getName();
}
```

→ printNameUpperCase 는 디폴트 메소드임, 인터페이스임에도 메소드 내용이 존재함

```java
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
```

→ 위에 인터페이스를 상속받은 클래스를 구현할 때 printNameUpperCase 를 구현하지 않아도 된다.

** 보통 인터페이스를 구현하고 사용하지 않는 메소도를 재 정의 하고 싶지 않기 때문에 추상 클래스를 만들고 거기다가 내용을 비워서 세팅해 놓는 경우도 있었다. Default 메소드를 통해서 그런 경우에 굳이 추상 클래스를 안 만들수 가 있다.

### 6. Stream

→ List를 Stream으로 읽어서 처리하는 방식으로 코드 간편화 및 병렬 처리에 장점이 있다.

```java
List<String> names = new ArrayList<>();
names.add("a");
names.add("b");
names.add("c");
names.add("d");

Stream<String> stringStream = 
     names.stream()
          .map(n - > n.toUpperCase());

// 메소드 레퍼런스 적용
Stream<String> stringStream = names.stream()
                .map(String::toUpperCase);

stringStream.forEach(s -> System.out.println(s));

// 메소드 레퍼런스 적용
stringStream2.forEach(System.out::println);
```

```java
// 중계 -> Stream 를 리턴한다.
// 종료 -> Stream x, collect 등을 리턴한다.

List<String> collect1 = names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());

Stream<String> stringStream1 = names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
});
        
collect1.forEach(s -> System.out.println(s));        
stringStream1.forEach(s -> System.out.println(s));
```

종료는 sysout 이 정의 하는 순간 호출되고

중계는 실제로 stream을 사용해서 forEach 하는 순간 sysout 이 호출된다.

** 병렬 Stream 

```java
List<String> collect = names.parallelStream().map((s) ->{
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());

        collect.forEach(System.out::println);
```

→ paralleStream 을 호출하면 Thread 가 서로 다른 것을 알 수 있다. 즉 병렬 처리를 한다는 뜻

→ Stream 으로 변경하면 Thread가 한개만 호출된다는 것을 알 수 있다.

### 7. Stream-api 정리

```java
List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1,"spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6,"The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> youngminEvents = new ArrayList<>();
        youngminEvents.add(springClasses);
        youngminEvents.add(javaClasses);

System.out.println("spring 으로 시작하는 수업----------");

springClasses.stream()
                        .filter(oc -> oc.getTitle().startsWith("spring"))
                                .forEach(oc -> System.out.println(oc.getTitle()));

System.out.println("close 되지 않은 수업-----------");

springClasses.stream()
                        .filter(oc -> !oc.isClosed())
                                .forEach(oc -> System.out.println(oc.getTitle()));

System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(oc -> oc.getTitle())
                .forEach(System.out::println);

System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
youngminEvents.stream().flatMap(Collection::stream)
                .forEach(oc -> System.out.println(oc.getId()));

youngminEvents.stream().flatMap(s -> s.stream())
                        .forEach(System.out::println);

System.out.println("10부터 1씩 증가하는 무제한 스트링 중에서 앞에 10개 뺴고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는 지 확인");
        boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle().contains("Test"));
        System.out.println(test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기");
        
        List<String> spring = springClasses.stream().filter(oc -> oc.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());

        spring.forEach(System.out::println);
```
