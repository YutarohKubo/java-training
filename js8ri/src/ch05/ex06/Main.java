package ch05.ex06;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        System.out.println("==========All Friday 13==========");
        LocalDate date = LocalDate.of(1900, 12, 31);
        for (;;) {
            date = date.with(next(w -> w.getDayOfMonth() == 13 && w.getDayOfWeek() == DayOfWeek.FRIDAY));
            if (date.getYear() > 2000) {
                // 最後の2001年最初の13金曜日を出力しないためにループ抜ける
                break;
            }
            System.out.println(date);
        }
    }

    /**
     * ch05 ex03のnext()
     */
    private static TemporalAdjuster next(Predicate<LocalDate> predicate) {
        return TemporalAdjusters.ofDateAdjuster(w -> {
            LocalDate resultDate = w;
            do {
                resultDate = resultDate.plusDays(1);
            } while (!predicate.test(resultDate));
            return resultDate;
        });
    }

}
