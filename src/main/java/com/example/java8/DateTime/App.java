package com.example.java8.DateTime;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        long time = date.getTime();
        System.out.println(date);
        System.out.println(time);

        Thread.sleep(100 * 2);
        Date after3Second = new Date();
        System.out.println(after3Second);
        after3Second.setTime(time);
        System.out.println(after3Second);

        // 객체의 상태를 바꿀 수 있다. Mutable


        // TYPE SAFETY 가 없다... 0 -> 1월
        Calendar youngminBirthDay = new GregorianCalendar(1993, Calendar.APRIL,24);
        System.out.println(youngminBirthDay.getTime());
        youngminBirthDay.add(Calendar.DAY_OF_YEAR, 1);
        System.out.println(youngminBirthDay.getTime());


        // 기계용 api
        Date date2 = new Date();
        long time1 = date2.getTime();
        System.out.println(time1);



        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat();

        // 기존
        // 명명이 알아보기가 힘들다.

        Instant instant = Instant.now();
        System.out.println(instant); // 기준시 UTC, GMT
        System.out.println(instant.atZone(ZoneId.of("UTC"))); // 기준시 UTC, GMT

        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = instant.atZone(zone);
        System.out.println(zonedDateTime);

        // 휴면
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        LocalDateTime birthDay
                = LocalDateTime.of(1982, Month.JULY, 15, 0, 0, 0);
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println(nowInKorea);

        Instant nowInstant = Instant.now();
        ZonedDateTime zonedDateTime1 = nowInstant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println(zonedDateTime1);

        //period duration
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2023, Month.APRIL, 24);

        Period period = Period.between(today, thisYearBirthday);
        System.out.println(period.getDays());

        Period until = today.until(thisYearBirthday);
        System.out.println(until.get(ChronoUnit.DAYS));

        Instant now2 = Instant.now();
        Instant plus = now2.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now2, plus);
        System.out.println(between.getSeconds());

        LocalDateTime now3 = LocalDateTime.now();
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now3.format(MMddyyyy));

        LocalDate parse = LocalDate.parse("07/15/1982", MMddyyyy);
        System.out.println(parse);

        // 변환가능
        Date date1 = new Date();
        Instant instant1 = date1.toInstant();
        Date newDate = Date.from(instant1);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        ZonedDateTime dateTime = gregorianCalendar.toInstant().atZone(ZoneId.systemDefault());
        GregorianCalendar from = GregorianCalendar.from(dateTime);

        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone timeZone = TimeZone.getTimeZone(zoneId);

        LocalDateTime now4 = LocalDateTime.now();
        LocalDateTime plus1 = now4.plus(10, ChronoUnit.DAYS);

    }
}
