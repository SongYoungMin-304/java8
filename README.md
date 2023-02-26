# java8

# 함수형 인터페이

## 함수형 인터페이스

```java
@FunctionalInterface
public interface RunSomething {
    
     // 메소드가 하나만 있으면 함수형 인터페이스
     int doIt(int number);

     // 인터페이스임에도 static 메소드 사용 가능
     static void printNmae(){
         System.out.println("youngmin");
     }

     default printAge(){
         System.out.println("30");
     }
}

```

```java
public interface RunSomething2 {
     public void doIt();
}
```

→ @FunctionalInterface 가 있으면 함수형 인터페이스라는 뜻으로 메소드가 여러개 생기면 컴파일 에러

**사용 예제**

```java
public class Foo

     public static void main(String[] args){

       RunSomething2 runSomething = new RunSomething2() {
               @Override
               public void doIt(){
                   System.out.println("Hello");
                   System.out.println("Lamda");
               }
       };

       RunSomething2 RunSomething2 = () -> {
                  System.out.println("Hello");
                  System.out.println("Lamda");
       };

       RumSomething runSomething3 = (number) -> {
                  return number + 10;
       };

       System.out.println(runSomething3.doIt(10));
       }
}
```

## 함수형 인터페이스 함수

**Function<Integer, Integer> → 입력값, 반환값 전부 Integer**

```java
Function<Integer, Integer> plus10_v2 = new Function<Integer, Integer>(){

      @Override
      public Integer apply(Integer integer){
              return integer + 10;
      }
};

plus10_v2.apply(1);
// 결과 값 11

Function<Integer, Integer> plus10 = (i) -> {
       return i + 10;
}

Function<Integer, Integer> multiplay2 = (i) -> {
       return i * 2;
}

Function<Integer, Integer> intergration = plus10.andThen(multiply2);

intergration.apply(1);
// 10을 더하고
// 곱하기 2를 함
// 22
```

**BiFunction → 입력값 두개를 받아서 1개 반환**

```java
BIFunction<Integer, Integer, Integer> bifunction = (a, b_ -> {
     return a + b;
}

bifunction.apply(10,20);
// 결과 값 30
```

**Consumer → 입력 값 1개만 받음**

```java
// 익명함수 방식
Consumer<Integer> c2 = new Consumer<Integer>(){
     @Override
     public void accept(Integer integer){
           System.out.println(integer);
     }
};

// 람다식
Consumer<Integer> c = (i) -> System.out.println(i);
c.accept(10);
```

## 람다식의 SCOPE 관련 내용

- 람다식은 전역변수와 같은 SCOPE를 가지고 있어서 전역변수명과 같은 지역변수 사용이 불가능 하다

```java
int baseNumber = 10;

Consumser<Integer? integerConsumser = new Consumser<Integer>(){

     @Override
     public void accept(Integer integer){
        int baseNumber = 11;
        System.out.println(baseNumber);
     }
};
// 익명 클래스에서는 가능

Consumser<Integer> printInt = (i) -> {
     //int baseNumber = 11; 같은 scope 여서 안됨
     System.out.println(i + baseNumber);
};
```

## 메소드 레퍼런스

```java
// 람다식으로 출력
Conumser<Integer> test1 = (s) -> {
     System.out.println(s);
};

// 메소드 레퍼런스로 변경
Consumer<Integer> test2 = System.out::println;

//UnaryOperator<T>
UnaryOperator<String> hi = (s) -> "hi" + s;

UnaryOperator<String> hi2 = Greeting::hi;

public static String hi(String name){
     return "hi" + name;
}

```

## **인터페이스 default 메소드**

```java
public interface Foo2 {

     void printName();

     default void printNameUpperCase(){
         System.out.println(getName().toUpperCase());
     }

     String getName();
}
```

→ 인터페이스 임에도 메소드 구현 가능

→ 상속받은 클래스를 구현할 때  해당 메소드 @Override 하지 않아도 된다.



# Stream
### Stream

→ List를 Stream으로 읽어서 처리하는 방식으로 코드 간편화 및 병렬 처리에 잠정이 있다.

```java
List<String> names = new ArrayList<>();
names.add("a");
names.add("b");
names.add("c");
names.add("d");

Stream<String> stringStream = names.stream()
                 .map(n -> n.toUpperCase());

Stream<String> stringStream = names.stream()
                 .map(String::toUpperCase);

stringStream.forEach(s - > System.out.println(s));
stringStream.forEach(System.out::println);

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

→ 종료는 sysout 이 정의하는 순간 호출

→ 중계는 실제로 Stream을 사용해서 forEach 하는 순간 sysout 호출됨

### 병렬 Stream

```java
List<String> collect = names.parallelStream().map((s) -> {
       System.out.println(s + " " + Thread.currentThread().getName());
       return s.toUpperCase();
    }).collect(Collectors.toList());

    conllect.forEach(System.out::println);
```

→ paralleStream을 호출하면 Thread가 서로 다른 것을 알 수 있다.(병렬처리)

→ Stream으로 변경하면 Thread가 한개만 호출된다는 것을 알 수 있다.

### Stream-api 정리

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
```

**spring 으로 시작하는 수업**

```java
springClasses.stream()
     .filter(oc -> oc.getTitle().startWith("spring"))
              .forEach(oc -> System.out.println(oc.getTtile()));
```

**close 되지 않은 수업**

```java
springClasses.stream()
     .filter(oc -> !oc.isClosed())
             .forEach(oc -> System.out.println(oc.getTitle()));
```

**수업 이름만 모아서 스트림 만들기**

```java
springClasses.stream()
       .map(oc -> oc.getTitle())
       .forEach(System.out::println);
```

**두 수업 목록에 들어있는 모든 수업 아이디 출력**

```java
youngminEvents.stream().flatMap(s - > s.stream())
                        .forEach(oc -> System.out.println(oc.getId)));

```

**10부터 1씩 증가하는 무제한 스트링 중에서 앞에 10개 빼고 최대 10개 까지만**

```java
Stream.iterate(10, i -> i + 1)
             .skip(10)
             .limit(10)
             .forEach(System.out::println);
```

**자바 수업 중에 Test가 들어있는 수업이 있는 지 확인**

```java
boolean test = javaClasses.stream().anyMatch(oc -> oc.getTitle()
                       .contains("Test"));

List<String> spring = springClasses.stream().filter(oc -> oc.getTitle().contains("spring"))
                     .map(OnlineClass::getTitle)
                     .collect(Collectors.toList();
```

# Optional

NullPointerException을 방지하게 해주는 기능

```java
public class OnlineClass {

     private Integer id;
     private String title;
     private boolean closed;
     private Progress progress;

     public OnlineClass(Integer id, String title, boolean closed){
          this.id = id;
          this.title = title;
          this.closed = closed;
     }

     // getter settter 존재
}
 
```

```java
public class Progress {

     private Duration studyDuration;
     private boolean finished;

     public Duration getStudyDuration(){
          return studyDuration'
     }

     public void setStudyDuration(Duration studyDuration){
          this.studyDuration = studyDuration;
     }
}
```

**IF 처리 관련 예제**

```java
OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);

Progress progress = spring_boot.getProgress();

if(progress != null){
     System.out.println(progress.getStudyDuration());
}
```

→ null 체크를 직접 하는 방식은 바람직하지 않음

### **Optional 사용**

**AS-IS**

```java
public Progress getProgress(){
     return Optional.ofNullable(progress);
}
```

**TO-BE**

```java
public Optional<Progress> getProgress() {
     return Optinal.ofNullable(progress);
}
```

**Optional 처리 관련 예제**

```java
List<OnlineClass> springClasses = new ArrayList<>();
      springClass.add(new OnlineClass(1, "spring boot" true));
      springClass.add(new OnlineClass(5, "rest api development" false));

Optinal<OnlineClass> optional = springClasses.stream().filter(oc - > oc.getTitle()
       .startWith("spring")).findFirst();

// 값 존재 여부 확인
optinal.isPresent();
optinal.isEmpty();

// 값 가져오기
progress.get();
progress.orElse(createNewClass()); //createNewClass 무조건 호출함
progress.orElseGet(App::createNewClass); //createNewClass 무조건 호출은 아니고 else일때 호출
progress.orElseThrow(() -> {
     return new IllegalArugmentException();
});

private static Progress createNewClass() {
        System.out.println("creating new online class");
        return new Progress~~
    }
```

# Date Time Api

### datetime 변경 이력

[java.util.Date](http://java.util.Date) > java.util.Calendar > java.time(org.joda.time)

### LocalDate, LocatTime. LocalDateTime

```java
LocalDate currentDate = LocalDate.now();
// result : 2019-11-13

LocatDate targetDate = LocalDate.of(2019, 11, 12);
// 결과 : 2019-11-12
```

```java
LocalTime currentTime = LocalTime.now();
// 결과 : 18:34:22

LocalTime targetTime = LocalTime.of(12,33,35,22);
// 끝에 4번째 매개변수는 nanoSecond 인데 선택 값이다, 굳이 쓰지 않아도 된다.
// 결과 : 12:32:33.0000022
```

```java
LocalDateTime currentDateTime = LocalDateTime.now();    
// 결과 : 2019-11-12T16:34:30.388

LocalDateTime targetDateTime = LocalDateTime.of(2019, 11, 12, 12, 32,22,3333);
// 여기도 second,nanoSecond 매개변수는 필수가 아닌 선택입니다.
// 결과 : 2019-11-12T12:32:22.000003333
```

### 날짜 더하기

```java
LocalDateTime currentDateTime = LocalDateTime.now();
// 더 하기는 plus***() 빼기는 minus***()
// currentDateTime.plusYears(long) or minusYears(long)
currentDateTime.plusDays(2)
// 결과 : 2019-11-14T12:32:22.000003333
```

| 리턴 타입 | 메소드(매개변수) | 설명 |
| --- | --- | --- |
| java.time.LocalDateTime | plusYears() | 년 |
| java.time.LocalDateTime | plusMonths() | 월 |
| java.time.LocalDateTime | plusWeeks() | 주 |
| java.time.LocalDateTime | plusDays() | 일 |
| java.time.LocalDateTime | plusHours() | 시 |
| java.time.LocalDateTime | plusMinutes() | 분 |
| java.time.LocalDateTime | plusSeconds() | 초 |
| java.time.LocalDateTime | plusNanos() | 밀리초 |

### 시간 비교

```java
LocalTime startTime = LocalTime.now();
// 결과 : 23:52:35
LocalTime endTime = LocalTime.of(23, 59, 59);
// 결과 : 23:59:59

// startTime이 endTime 보다 이전 시간 인지 비교
startTime.isBefore(endTime);    
// 결과 : true

// startTime이 endTime 보다 이후 시간 인지 비교
startTime.isAfter(endTime); 
// 결과 : false
```

### 날짜 차이 계산

```java
LocalDate startDate = LocalDate.now(); 
// 결과 : 2019-11-12
LocalDate endDate = LocalDate.of(2019,12,13);
// 결과 : 2019-12-13

Period period = Period.between(startDate, endDate);

period.getYears();      // 0년
period.getMonths();     // 1개월
period.getDays();       // 1일 차이
```

→ startDate와 end가 31일 차이가 나서 리턴이 31일이 되는 것이 아니라 1개월 1일로 변환됩니다.

```java
LocalDate startDate = LocalDate.now(); 
// 결과 : 2019-11-12
LocalDate endDate = LocalDate.of(2019,12,13);
// 결과 : 2019-12-13

ChronoUnit.DAYS.between(startDate, endDate); 
// 결과 : 31 (1개월 1일)
```

| 클래스 | 설명 |
| --- | --- |
| ChronoUnit.YEARS | 전체 년 차이 |
| ChronoUnit.MONTHS | 전체 월 차이 |
| ChronoUnit.WEEKS | 전체 주 차이 |
| ChronoUnit.DAYS | 전체 일 차이 |
| ChronoUnit.HOURS | 전체 시간 차이 |
| ChronoUnit.SECONDS | 전체 초 차이 |
| ChronoUnit.MILLIS | 전체 밀리초 차이 |
| ChronoUnit.NANOS | 전체 나노초 차이 |

```java
LocalTime startTime = LocalTime.now();  
// 결과 : 17:14:55
LocalTime endTime = LocalTime.of(18,17,35);
// 결과 : 18:17:35

Duration duration = Duration.between(startTime, endTime);
duration.getSeconds();      
// 결과 : 3742
duration.getNano();
// 결과 : 922000000
```

### 날짜 포맷

```java
LocalDateTime now = LocalDateTime.now();
DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h시 m분");
String nowString = now.format(dateTimeFormatter);

// 결과 : 2019년 11월 12일 오후 7시 2분

LocalDateTime now2 = LocalDateTime.now();  
DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
// 결과 : 2019-11-12 07:26:12
```
