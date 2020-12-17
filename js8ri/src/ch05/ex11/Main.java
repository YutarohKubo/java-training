package ch05.ex11;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm VV");

    public static void main(String[] args) {
        ZonedDateTime timeFrank = ZonedDateTime.parse("2020-12-18 14:05 Europe/Berlin", formatter);
        ZonedDateTime timeLA = ZonedDateTime.parse("2020-12-18 16:40 America/Los_Angeles", formatter);
        Duration duration = Duration.between(timeFrank, timeLA);
        System.out.println("Flight time = " + String.format("%d hour %d minutes", duration.toHours(), duration.toMinutes() % 60));
    }

}
