package ch06.ex05;

import java.awt.*;

public enum SignalColor {
    GREEN {
        @Override
        public Color getColor() {
            return Color.GREEN;
        }
    },
    YELLOW {
        @Override
        public Color getColor() {
            return Color.YELLOW;
        }
    },
    RED {
        @Override
        public Color getColor() {
            return Color.RED;
        }
    };

    public abstract Color getColor();
}
