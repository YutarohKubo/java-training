package ch05.ex03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate adjustNextTime = today.with(next(w -> w.getDayOfWeek().getValue() < 6));
        System.out.println("adjustTime = " + adjustNextTime);
    }

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
