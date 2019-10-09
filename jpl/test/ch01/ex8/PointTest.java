package ch01.ex8;

import ch01.ex08.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {

    Point p1, p2;

    @BeforeEach
    void setup () {
        p1 = new Point();
        p2 = new Point();
        p1.setPosition(1.0, 4.0);
        p2.setPosition(-3.5, -8.2);
    }

    @Test
    void setPositionP1() {
        assertEquals(1.0, p1.x);
        assertEquals(4.0, p1.y);
    }

    @Test
    void setPositionP2 () {
        assertEquals(-3.5, p2.x);
        assertEquals(-8.2, p2.y);
    }
}