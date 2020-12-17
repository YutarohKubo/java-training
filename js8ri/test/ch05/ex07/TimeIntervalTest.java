package ch05.ex07;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class TimeIntervalTest {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void overLappingTest1() {
        TimeInterval interval1 = new TimeInterval(
                LocalDateTime.parse("2020-12-18 09:00", formatter),
                LocalDateTime.parse("2020-12-18 11:00", formatter)
        );
        TimeInterval interval2 = new TimeInterval(
                LocalDateTime.parse("2020-12-18 08:00", formatter),
                LocalDateTime.parse("2020-12-18 09:30", formatter)
        );
        assertTrue(interval1.isOverlapped(interval2));
        assertTrue(interval2.isOverlapped(interval1));
    }

    @Test
    public void overLappingTest2() {
        TimeInterval interval1 = new TimeInterval(
                LocalDateTime.parse("2020-12-18 14:00", formatter),
                LocalDateTime.parse("2020-12-18 17:00", formatter)
        );
        TimeInterval interval2 = new TimeInterval(
                LocalDateTime.parse("2020-12-18 16:00", formatter),
                LocalDateTime.parse("2020-12-18 18:00", formatter)
        );
        assertTrue(interval1.isOverlapped(interval2));
        assertTrue(interval2.isOverlapped(interval1));
    }

    @Test
    public void overLappingTest3() {
        TimeInterval interval1 = new TimeInterval(
                LocalDateTime.parse("2020-12-18 14:00", formatter),
                LocalDateTime.parse("2020-12-18 17:00", formatter)
        );
        TimeInterval interval2 = new TimeInterval(
                LocalDateTime.parse("2020-12-18 10:00", formatter),
                LocalDateTime.parse("2020-12-18 18:00", formatter)
        );
        assertTrue(interval1.isOverlapped(interval2));
        assertTrue(interval2.isOverlapped(interval1));
    }

    @Test
    public void notOverLappingTest1() {
        TimeInterval interval1 = new TimeInterval(
                LocalDateTime.parse("2020-12-18 09:00", formatter),
                LocalDateTime.parse("2020-12-18 11:00", formatter)
        );
        TimeInterval interval2 = new TimeInterval(
                LocalDateTime.parse("2020-12-18 08:00", formatter),
                LocalDateTime.parse("2020-12-18 08:30", formatter)
        );
        assertFalse(interval1.isOverlapped(interval2));
        assertFalse(interval2.isOverlapped(interval1));
    }

    @Test
    public void notOverLappingTest2() {
        TimeInterval interval1 = new TimeInterval(
                LocalDateTime.parse("2020-12-18 09:00", formatter),
                LocalDateTime.parse("2020-12-18 11:00", formatter)
        );
        TimeInterval interval2 = new TimeInterval(
                LocalDateTime.parse("2020-12-18 12:00", formatter),
                LocalDateTime.parse("2020-12-18 13:30", formatter)
        );
        assertFalse(interval1.isOverlapped(interval2));
        assertFalse(interval2.isOverlapped(interval1));
    }

    @Test
    public void notOverLappingTest3() {
        TimeInterval interval1 = new TimeInterval(
                LocalDateTime.parse("2020-12-18 09:00", formatter),
                LocalDateTime.parse("2020-12-18 11:00", formatter)
        );
        TimeInterval interval2 = new TimeInterval(
                LocalDateTime.parse("2020-12-18 08:00", formatter),
                LocalDateTime.parse("2020-12-18 09:00", formatter)
        );
        assertFalse(interval1.isOverlapped(interval2));
        assertFalse(interval2.isOverlapped(interval1));
    }

    @Test
    public void testException1() {
        assertThrows(IllegalArgumentException.class, () -> new TimeInterval(
                LocalDateTime.parse("2020-12-18 09:00", formatter),
                LocalDateTime.parse("2020-12-18 08:00", formatter)
        ));
    }

    @Test
    public void testException2() {
        assertThrows(IllegalArgumentException.class, () -> new TimeInterval(
                LocalDateTime.parse("2020-12-18 09:00", formatter),
                LocalDateTime.parse("2020-12-18 09:00", formatter)
        ));
    }

}
