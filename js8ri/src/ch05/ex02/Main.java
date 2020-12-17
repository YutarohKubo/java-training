package ch05.ex02;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2000, 2, 29);
        LocalDate localDate1year = localDate.plusYears(1);
        LocalDate localDate4year = localDate.plusYears(4);
        System.out.println("+1year to 2000/02/29 : " + localDate1year);
        System.out.println("+4year to 2000/02/29 : " + localDate4year);
        System.out.println("\n");
        System.out.println("======+1-4year to 2000/02/29======");
        for (int i = 0; i < 4; i++) {
            localDate = localDate.plusYears(1);
            System.out.println("+" + (i + 1) + "year to 2000/02/29 : " + localDate);
        }
    }

}
