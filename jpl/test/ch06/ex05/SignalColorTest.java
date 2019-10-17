package ch06.ex05;

import ch06.ex04.SignalColor;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SignalColorTest {
    @Test
    void getColorTest () {
        assertEquals(SignalColor.RED.getColor(), Color.RED);
    }
}