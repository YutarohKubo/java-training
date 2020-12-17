package ch05.ex05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {
        System.out.println(LocalDate.of(1993, 3, 3).until(LocalDate.now(), ChronoUnit.DAYS) + "日");
    }

}
