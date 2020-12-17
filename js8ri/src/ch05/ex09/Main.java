package ch05.ex09;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {

    public static void main(String[] args) {
        ZoneId.getAvailableZoneIds().stream().filter(t -> {
            ZonedDateTime nowTime = ZonedDateTime.now(ZoneId.of(t));
            return nowTime.getOffset().getTotalSeconds() < 3600 &&
                    nowTime.getOffset().getTotalSeconds() > -3600;
        }).forEach(action -> {
            ZonedDateTime nowTime = ZonedDateTime.now(ZoneId.of(action));
            System.out.println(action + " ; " + nowTime + " : " + nowTime.getOffset());
        });
    }

}
