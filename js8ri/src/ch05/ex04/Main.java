package ch05.ex04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while (true) {
                System.out.println("Please enter command.(example => Cal 3 2013)");
                String command = reader.readLine();
                String[] commands = command.split(" +");
                if (commands.length == 3 && commands[0].equals("Cal")) {
                    try {
                        int year = Integer.parseInt(commands[2]);
                        int month = Integer.parseInt(commands[1]);
                        createCal(year, month);
                        break;
                    } catch (NumberFormatException | DateTimeException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * カレンダー作成する
     * @param year 年
     * @param month 月
     */
    private static void createCal(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        int startColNum = date.getDayOfWeek().getValue() % 7;
        int lengthOfMonth = date.lengthOfMonth();
        for (int i = 0; i < startColNum; i++) {
            System.out.print("    ");
        }
        for (;; date = date.plusDays(1)) {
            System.out.print(String.format("%4d", date.getDayOfMonth()));
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                System.out.println();
            }
            if (date.getDayOfMonth() == lengthOfMonth) {
                break;
            }
        }
    }

}
