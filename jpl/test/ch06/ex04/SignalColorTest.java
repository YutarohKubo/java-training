package ch06.ex04;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SignalColorTest {

    @Test
    void getColorTest () {
        assertEquals(SignalColor.GREEN.getColor(), Color.GREEN);
    }

}