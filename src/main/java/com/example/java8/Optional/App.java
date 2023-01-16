package com.example.java8.Optional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1,"spring boot", true));
/*        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));*/
        springClasses.add(new OnlineClass(5, "rest api development", false));

        Optional<OnlineClass> optional = springClasses.stream().filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        boolean present = optional.isPresent();
        boolean present2 = optional.isEmpty();
        System.out.println(present);
        System.out.println(present2);
        System.out.println("===================");

        if(optional.isPresent()) {
            OnlineClass onlineClass = optional.get();
            System.out.println(onlineClass.getTitle());
        }

        // Consumer 방식
        optional.ifPresent(oc -> System.out.println(oc.getTitle()));

        System.out.println(optional.isEmpty());

        // createNewClass는 무조건 실행이 된다.
        OnlineClass onlineClass = optional.orElse(createNewClass());

        System.out.println(onlineClass.getTitle());

        System.out.println("--------------------------------------------");

        OnlineClass onlineClass1 = optional.orElseGet(App::createNewClass);
        System.out.println(onlineClass1.getTitle());

        optional.orElseThrow(() -> {
            return new IllegalArgumentException();
        });

        optional.orElseThrow(IllegalStateException::new);

        // filter 조건
        Optional<OnlineClass> onlineClass2 = optional.filter(oc -> oc.getId() > 0);

        System.out.println(onlineClass2.isEmpty());

        Optional<OnlineClass> onlineClass3 = optional.filter(OnlineClass::isClosed);

        System.out.println(onlineClass3.isEmpty());

        // map
        Optional<Integer> integer = optional.map(OnlineClass::getId);
        System.out.println(integer.isPresent());


        Optional<Optional<Progress>> progress = optional.map(OnlineClass::getProgress);
        Optional<Progress> progress1 = progress.orElse(Optional.empty());

        // Optinal 을 두번 까준다....
        Optional<Progress> progress2 = optional.flatMap(OnlineClass::getProgress);


/*
        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);

        Optional<Progress> progress = spring_boot.getProgress();
*/


        /*Duration studyDuration = spring_boot.getProgress().getStudyDuration();
        System.out.println(studyDuration);

        // 예전 NULL 체크 client 코드가 null 체크를 진행함
        Progress progress = spring_boot.getProgress();
        if(progress !=null){
            System.out.println(progress.getStudyDuration());
        }*/



    }

    private static OnlineClass createNewClass() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New Class", false);
    }
}
