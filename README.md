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
