# java8

# 함수형 인터페이스

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

# Thread

- 스레드(thread)란 프로세스(process) 내에서 실제로 작업을 수행하는 주체를 의미합니다.
- 모든 프로세스에는 한 개 이상의 스레드가 존재하여 작업을 수행합니다.

또한, 두 개 이상의 스레드를 가지는 프로세스를 멀티스레드 프로세스(multi-threaded process)라고 합니다.

### Thread 사용 방법

**1) Thread 상속받기**

```java
public class ExtendThread extends Thread{
     
     public void run(){
          System.out.println("ExtendsThread Start");
     }

public static void main(String[] args){
     ExtendsThread et = new ExtendsThread();
     et.start();
}

```

**2) Runnable 상속 받기**

```java
public class ImplThread implements Runnable{

     @Override
     public void run(){
          System.out.println("ExtendsThread Start");
     }

public static void main(String[] args){
     Thread thread = new Thread(new ImpleThread());
     thread.start()
}
```

→ 인터페이스를 상속받으면 다중 상속 가능하다.

### Executors 사용 방법

- Thread를 만들고 관리하는 것을 Api Executors에게 위임합니다.
- Runnable 만 개발자가 만들고 생성, 종료, 없애기 작업들을 Executors가 해줍니다.

```java
ExecutorService executorService = Executors.newSingleThreadExecutor();

executorService.submit(() -> System.out.println("Thread Test :" + Thread.currentThread().getName()));

executorService.submit(() -> System.out.println("Thread Test :" + Thread.currentThread().getName()));

executorService.shutdown();

// 결과 값
Thread Test :pool-1-thread-1
Thread Test :pool-1-thread-1

```

→ 하나의 스레드가 일을 진행해준다.

→ shutdown은 우아한 종료로 하던 작업이 다 끝나면 종료해준다.

- **shutdownNow 를 호출하면 그냥 작업을 하고 있던 말던 종료한다.**

```java
ExecutorService executorService = Executors.newFixedThreadPool(2);

executorService.submit(addRunnable(1,2));

executorService.submit(addRunnable(1,3));

executorService.submit(addRunnable(1,4));

executorService.submit(addRunnable(1,5));

executorService.shutdown();

// 결과 값
result: 3 (pool-1-thread-1) 
result: 4 (pool-1-thread-2) 
result: 5 (pool-1-thread-2) 
result: 6 (pool-1-thread-2)

private static Runnable addRunnable(int num1, int num2){
        return () -> System.out.println("result: " + (num1 + num2) + " (" + Thread.currentThread().getName() + ") ");
}
```

→ 두개의 메소드가 일을 나눠서 진행한다.

```java
ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

System.out.println("실행" + LocalDateTime.now());

// 1초 기다렸다가 1초에 한 번씩 실행
scheduledExecutorService.scheduleAtFixedRate(printRunnable("스레드 테스트"),1,1, TimeUnit.SECONDS);

try{
    Thread.sleep(10000L);
  }catch (Exception e){
            System.out.println(e);
}

scheduledExecutorService.shutdown();

// 결과 값
실행2023-02-27T21:40:25.703463600
스레드 테스트 (pool-1-thread-1, 2023-02-27T21:40:26.721811800) 
스레드 테스트 (pool-1-thread-1, 2023-02-27T21:40:27.718921400) 
스레드 테스트 (pool-1-thread-1, 2023-02-27T21:40:28.731032300) 
스레드 테스트 (pool-1-thread-1, 2023-02-27T21:40:29.727121500) 
스레드 테스트 (pool-1-thread-1, 2023-02-27T21:40:30.721804700) 
스레드 테스트 (pool-1-thread-1, 2023-02-27T21:40:31.718342400) 
스레드 테스트 (pool-1-thread-1, 2023-02-27T21:40:32.727992400) 
스레드 테스트 (pool-1-thread-1, 2023-02-27T21:40:33.724149300) 
스레드 테스트 (pool-1-thread-1, 2023-02-27T21:40:34.718175200) 
스레드 테스트 (pool-1-thread-1, 2023-02-27T21:40:35.729977200)
```

→ 주기적으로 호출되는 Thread

### Future 사용 방법

- Future -Blocking 방식의 작업 완료 통보
- Future 객체는 작업이 완료 될 때까지 기다렸다가 최종 결과를 얻는데 사용한다. 이를 지연완료 객체라고 한다.

```java
ExecutorService service = Executors.newSingleThreadExecutor();
Future thread = service.submin(printRunnable("Thread"));

thread.get();
System.out.println("후처리);

private static Runnable printRunnable(String message) throws Exception{
        return () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(message + " (" + Thread.currentThread().getName() + ", " + LocalDateTime.now() + ") ");
        };
    }

// 결과 값
// 후처리가 printRunnable 이 끝난 이후에 호출된다
```

```java
ExecutorService service = Executors.newSingleThreadExecutor();
Future<String> submit = service.submin(returnRunnable());

String result = submit.get()

System.out.println(s);

private static Runnable addRunnable(int num1, int num2){
        return () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("result: " + (num1 + num2) + " (" + Thread.currentThread().getName() + ") ");
        };
    }

// 결과 값
// thread 종료 이후에 호출됨
```

→ 스레드가 종료될 때까지 기다려 주는 처리 라고 보면 된다.

**(특정 스레드가 끝나야지만 다음 처리가 되야할 경우 사용 하면 좋을 듯)**

### Future 사용 방법

- Future -Blocking 방식의 작업 완료 통보
- Future 객체는 작업이 완료 될 때까지 기다렸다가 최종 결과를 얻는데 사용한다. 이를 지연완료 객체라고 한다.

```java
ExecutorService service = Executors.newSingleThreadExecutor();
Future thread = service.submin(printRunnable("Thread"));

thread.get();
System.out.println("후처리);

private static Runnable printRunnable(String message) throws Exception{
        return () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(message + " (" + Thread.currentThread().getName() + ", " + LocalDateTime.now() + ") ");
        };
    }

// 결과 값
// 후처리가 printRunnable 이 끝난 이후에 호출된다
```

```java
ExecutorService service = Executors.newSingleThreadExecutor();
Future<String> submit = service.submin(returnRunnable());

String result = submit.get()

System.out.println(s);

private static Runnable addRunnable(int num1, int num2){
        return () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("result: " + (num1 + num2) + " (" + Thread.currentThread().getName() + ") ");
        };
    }

// 결과 값
// thread 종료 이후에 호출됨
```

→ 스레드가 종료될 때까지 기다려 주는 처리 라고 보면 된다.

**(특정 스레드가 끝나야지만 다음 처리가 되야할 경우 사용 하면 좋을 듯)**

### CompletableFuture

Future의 제약

1. Future를 외부에서 완료시킬 수 없다.
2. 블로킹 코드(get()) 이후에 콜백 등을 정의해서 사용해야한다.

 (Future에서는 return 되는 결과 값을 가지고 무언가를 하려면 get() 이후에만 가능하다.

1. 여러 Future 조합을 사용할 수 없다.
2. 예외 처리용 API를 제공하지 않는다.

CompletableFuture의 장점

1. Future와 달리 외부에서 명시적으로 Complete를 시켜버릴 수 있다.
2. CompletableFuture를 사용하면 명시적으로 Executors 를 사용할 필요가 없어진다.(fork Join)

**→ 즉 결론적으로 비동기 처리를 할 때 특정 스레드를 기다리는 등으로 사용하던 Future를 보다 편리하게 사용할 수 있게 하고 다양한 기능을 추가해준 JAVA8의 신규 기능?**

### 1) runAsync(반환값 없음)

```java
CompletableFuture<Void> tmp1 = CompletableFuture.runAsync(() ->{
     System.out.println("runAsync " + Thread.currentThread().getName());
});

tmp1.get();

CompletableFuture<Void> tmp1 = CompletableFuture.runAsync(runnableSample());
// 위와 동일하게 사용 가능..

private static Runnable runnableSample(){
  Runnable runnable = new Runnable() {
   @Override
   public void run() {
    System.out.println("runAsync " + Thread.currentThread().getName());
   }
  };
    return runnable;
 }

private static Runnable runnableSample(){
   return () -> {
     System.out.println("runAsync " + Thread.currentThread().getName());
   }
}
```

구현체

```java
-- 실제 구현체
public static CompletableFuture<Void> runAsync(Runnable runnable) {
        return asyncRunStage(ASYNC_POOL, runnable);
    }

```

결과 값

```java
runAsync ForkJoinPool.commonPool-worker-19
```

### 2) supplyAsync(반환값 있음)

```java
CompletableFuture<String> tmp2 = CompletableFuture.supplyAsync(callableSample());

// 같은 소스
CompletableFuture<String> tmp3 = CompletableFuture.supplyAsync(() -> {
     return "song";
});

String s = tmp2.get();
System.out.println(s);

private static Supplier<String> callableSample(){
  Supplier<String> supplier = new Supplier<String>() {
   @Override
   public String get() {
    return "song";
   }
  };

```

구현체

```java
public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
        return asyncSupplyStage(ASYNC_POOL, supplier);
    }
```

결과 값

```java
song
```

***번외

```java
xxxx.print(() -> {
    System.out.println("테스트");
});

private static void print(interfaceTest interfaceTest){
  interfaceTest.print2();
  System.out.println("끝");
 }

public interface interfaceTest {
    public void print2();
}
```

→ 인터페이스를 만들고 메소드에서 인터페이스의 메소드를 호출하게 만든다.

→ 실제로 print를 호출할 때 구현체만 호출하면 구현체에 있는 print2를 호출

### 3) thenApply(CompletableFuture 연결, Function)

```java
CompletableFuture<String> tmp3 = CompletableFuture.supplyAsync(()
->{
      System.out.println("callback " + Thread.currentThread().getName());
      return "CallBack";
  }).thenApply((s) -> {
    System.out.println(Thread.currentThread().getName());
    return s.toUpperCase();
});

CompletableFuture<String> tmp77 = CompletableFuture.supplyAsync(()
    ->{
   System.out.println("callback " + Thread.currentThread().getName());
   return "CallBack";
  }).thenApply(addFunction());

private static Function addFunction(){
  Function<String, String> function = new Function<String, String>() {
   @Override
   public String apply(String s) {
    return s.toUpperCase();
   }
  };

    
```

→ 스레드가 실행 된 이후에 결과 값을 가지고 다시 한번 처리(thenApply)

→ future 에서는 get() 이후에 호출했어야 했

구현체

```java
public <U> CompletableFuture<U> thenApply(
        Function<? super T,? extends U> fn) {
        return uniApplyStage(null, fn);
    }
```

결과 값

```java
callback ForkJoinPool.commonPool-worker-19
ForkJoinPool.commonPool-worker-19
```

→ thenApply 와 completableFuture 동일한 스레드 사용

### 4) thenAccept(CompletableFuture 연결, Consumser)

```java
CompletableFuture<Void> tmp4 = CompletableFuture.supplyAsync(() ->{
   System.out.println("callback2 " + Thread.currentThread().getName());
   return "callback2";
  }).thenAccept((k) -> {
   //Consumser
   System.out.println(Thread.currentThread().getName());
   System.out.println(k.toUpperCase());
  });

  tmp4.get();
```

구현체

```java
public CompletableFuture<Void> thenAccept(Consumer<? super T> action) {
        return uniAcceptStage(null, action);
    }
```

결과 값

```java
callback ForkJoinPool.commonPool-worker-19
ForkJoinPool.commonPool-worker-19
```

→ thenApply 와 completableFuture 동일한 스레드 사용

### 5) thenRun(CompletableFuture 연결, Consumser)

```java
CompletableFuture<Void> tmp5 = CompletableFuture.supplyAsync(() ->{
            System.out.println("callback3" + Thread.currentThread().getName());
            return "callback3";
        }).thenRun(() ->{
            System.out.println("thenRun" + Thread.currentThread().getName());
        }); // 결과 값을 참조하지 않는다 Runnable

        tmp5.get();
```

구현체

```java
public CompletableFuture<Void> thenRun(Runnable action) {
        return uniRunStage(null, action);
    }
```

결과 값

```java
callback3ForkJoinPool.commonPool-worker-19
thenRunForkJoinPool.commonPool-worker-19
```

### ForkJoinPool

→ 어떻게 스레드 풀을 만들지 않고 별도의 스레드들로 동작을 하는 걸까?

→ ForkJoinPool 때문에 가능한다.

* ForkJoinPool 이란 Executor 구현체 중 하나인데, Deque를 통하여 자기 스레드가
할 일이 없으면, Deque에서 가져와서 처리하는 방식의 프로엠 워크
자기가 파생시킨 서브 태스크들을 다른 스레드들에 분산시켜 처리하고 모아서 Join 하는 식으로 작업단위를 구성

### 6) 스레드 풀을 직접 만들어 진행해 해보기

```java
ExecutorService executorService2 = Executors.newFixedThreadPool(4);
  CompletableFuture<String> tmp6 = CompletableFuture.supplyAsync(() ->{
   System.out.println("ThreadPool " + Thread.currentThread().getName());
   return " ThreadPool";
  }, executorService2).thenApply((s)->{
   System.out.println("ThreadPool " + Thread.currentThread().getName());
   return "new"+s;
  });

System.out.println(tmp6.get());

executorService2.shutdown();*/
```

→ executorService2 로 정의한 스레드를 통해서 실행한값

결과 값

```java
ThreadPool pool-1-thread-1
ThreadPool pool-1-thread-1
new ThreadPool
```


### 7**) CompletableFuture 여러 개 조합**

```java
CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
     System.out.println("Hello" + Thread.currentThread().getName());
     return "Hello";
});

CompletableFuture<String> world = hello.thenCompose((s) -> getWorld(s));
// hello 다음에 getWorld 호출하게 처리

// static 으로 getWorld 정의
private static CompletableFuture<String> getWorld(String message) {
     return CompletableFupter.supplyAsync(() -> {
         System.out.println(message + Thread.currentThread().getName());
         return message + "World";
     });
}

String s = world.get();

```

→ thenCompose로 hello 이후에 getWorld 를 실행하는 스레드를 정의

결과 값

```java
HelloForkJoinPool.commonPool-worker-19
HelloForkJoinPool.commonPool-worker-19
HelloWorld
```

### 8**) CompletableFuture 두 작업을 독립적으로 실행하고, 둘 다 종료했을때**

```java
CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
      System.out.println("Hello" + Thread.currentThread().getName());
      return "Hello";
});

CompletableFuture<String> world = hello.thenCompose((s) -> getWorld(s));
// hello 다음에 getWorld 호출하게 처리

CompletableFuture<String> future = hello.thenCombine(world, (h, w) -> {
    return h + " " + w;
});

// static 으로 getWorld 정의
private static CompletableFuture<String> getWorld(String message) {
     return CompletableFupter.supplyAsync(() -> {
         System.out.println(message + Thread.currentThread().getName());
         return message + "World";
     });
}

String s = future.get();
System.out.println(s);
```

→ hello 와 world를 정의하고 두개가 다 끝나면 두개의 결과 값을 합쳐서 출력하는 CompletableFuture 정의

결과

```java
HelloForkJoinPool.commonPool-worker-19
HelloForkJoinPool.commonPool-worker-19
Hello HelloWorld
```

### 9**) 여러 작업을 모두 실행하고 모든 작업 결과에 콜백실행하려면?**

```java
CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
    System.out.println("Hello" + Thread.currentThread().getName());
    return "Hello";
});

CompletableFuture<String> world = hello.thenCompose((s) -> getWorld(s));

List<CompletableFuture<String>> futures = Arrays.asList(hello, world);

CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

CompletableFuture<List<String>> result = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> {
                    return futures.stream()
                            .map((s) -> s.join())  // CompletableFuture::join
                            .collect(Collectors.toList());
                });
        
        result.get().forEach((s) -> System.out.println(s)); //System.out::println

// static 으로 getWorld 정의
private static CompletableFuture<String> getWorld(String message) {
     return CompletableFupter.supplyAsync(() -> {
         System.out.println(message + Thread.currentThread().getName());
         return message + "World";
     });
}
```

→ 여러개 작업을 실행하고 콜백을 받는다.

결과

```java
HelloForkJoinPool.commonPool-worker-19
HelloForkJoinPool.commonPool-worker-19
Hello
HelloWorld
```


### 10**) 여러 작업 중 가장 빨리 끝난 하나의 결과에 콜백을 실행**

```java
CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
   System.out.println("Hello" + Thread.currentThread().getName());
   return "Hello";
  });

CompletableFuture<String> world = hello.thenCompose((s) -> getWorld(s));

List<CompletableFuture<String>> futures = Arrays.asList(hello, world);

CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

CompletableFuture<Void> voidCompletableFuture = CompletableFuture.anyOf(futuresArray)
    .thenAccept(System.out::println);

voidCompletableFuture.get();
```

결과

```java
HelloForkJoinPool.commonPool-worker-19
Hello
```

→ hello 가 먼저 끝남

### 11**) 예외처리**

```java
boolean throwError = true;

CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
     if(throwError) {
       throw new IllegalArgumentException();
     }

     System.out.println("Hello " + Thread.currentThread().getName());
   return "hello";
  }).exceptionally(ex -> {
     System.out.println(ex);
     return "Error";
  });

System.out.println(future.get());
  

```

결과

```java
java.util.concurrent.CompletionException: java.lang.IllegalArgumentException
Error
```

### 12**) 예외처리-2**

```java
boolean throwError = false;

  CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
   if(throwError){
    throw new IllegalArgumentException();
   }
   System.out.println("Hello " +Thread.currentThread().getName());
   return "Hello";
  }).handle((result, ex) -> {
   if(ex != null){
    System.out.println(ex);
    return "Error";
   }
   return result;
  });

System.out.println(hello.get());
```

결과

```java
Hello ForkJoinPool.commonPool-worker-19
Hello
```

→ handler 를 통해서 성공, 실패 처리
