package ch06.ex04;

import java.awt.*;

public enum SignalColor {
    GREEN(Color.GREEN),
    YELLOW(Color.YELLOW),
    RED(Color.RED);

    Color color;
    SignalColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
