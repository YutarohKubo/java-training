package ch05.ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String yearStr = "";
        int inputYear = -1;
        while (inputYear < 0) {
            System.out.print("input year : ");
            try {
                yearStr = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputYear = Integer.parseInt(yearStr);
                if (inputYear < 0) {
                    System.out.println("Please input year ( > 0)");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input number");
            }
        }
        LocalDateTime programmersDay
                = LocalDateTime.of(inputYear, 1, 1, 0, 0, 0)
                .plusHours(24 * 255);
        System.out.println("programmersDay : " + programmersDay);
    }

}
