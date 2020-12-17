package ch05.ex10;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm VV");

    public static void main(String[] args) {
        ZonedDateTime timeLA = ZonedDateTime.parse("2020-12-18 03:05 America/Los_Angeles", formatter);
        timeLA = timeLA.plusHours(10).plusMinutes(50);
        ZonedDateTime timeFrank = timeLA.withZoneSameInstant(ZoneId.of("Europe/Berlin"));
        System.out.println("Arrive in Frankfurt at " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(timeFrank));
    }

}
