package ch05.ex08;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {

    public static void main(String[] args) {
        ZoneId.getAvailableZoneIds().stream().forEach(action -> {
            ZonedDateTime nowTime = ZonedDateTime.now(ZoneId.of(action));
            System.out.println(action + " ; " + nowTime + " : " + nowTime.getOffset());
        });
    }

}
