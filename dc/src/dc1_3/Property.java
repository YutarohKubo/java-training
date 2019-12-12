package dc1_3;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Property {

    private static final int DEFAULT_FONT_STYLE = Font.PLAIN;
    public static final int LARGE_FONT_SIZE = 96;
    public static final int DEFAULT_FONT_SIZE = 48;
    public static final int SMALL_FONT_SIZE = 24;
    public static final int MAX_RGB_VALUE = 255;
    public static final int MAX_FONT_SIZE = 500;
    public static final Color DEFAULT_TIME_COLOR = AppStyle.VERT_PRAIRIE;
    private static final Font DEFAULT_TIME_FONT = AppStyle.TIMES_ROMAN;
    private static final Color DEFAULT_BACKGROUND_COLOR = AppStyle.BLACK;

    private static final AppColor[] appColors = new AppColor[]
            {
                    new AppColor(Color.BLACK, "BLACK"),
                    new AppColor(Color.BLUE, "BLUE"),
                    new AppColor(Color.CYAN, "CYAN"),
                    new AppColor(Color.DARK_GRAY, "DARK_GRAY"),
                    new AppColor(Color.GRAY, "GRAY"),
                    new AppColor(Color.GREEN, "GREEN"),
                    new AppColor(Color.LIGHT_GRAY, "LIGHT_GRAY"),
                    new AppColor(Color.MAGENTA, "MAGENTA"),
                    new AppColor(Color.ORANGE, "ORANGE"),
                    new AppColor(Color.PINK, "PINK"),
                    new AppColor(Color.RED, "RED"),
                    new AppColor(Color.WHITE, "WHITE"),
                    new AppColor(Color.YELLOW, "YELLOW")
            };
    private static final AppFontSize[] appFontSize = new AppFontSize[]{
            new AppFontSize(LARGE_FONT_SIZE, "Large"),
            new AppFontSize(DEFAULT_FONT_SIZE, "Middle"),
            new AppFontSize(SMALL_FONT_SIZE, "Small"),
    };
    private Color timeColor = DEFAULT_TIME_COLOR;
    private Font timeFont = DEFAULT_TIME_FONT;
    private Color backgroundColor = DEFAULT_BACKGROUND_COLOR;

    public static class AppColor {
        private Color color;
        private String name;

        AppColor(Color color, String name) {
            this.color = color;
            this.name = name;
        }

        public Color getColor() {
            return color;
        }

        public String getName() {
            return name;
        }
    }

    public static class AppFontSize {
        private int size;
        private String name;

        AppFontSize(int size, String name) {
            this.size = size;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getSize() {
            return size;
        }
    }

    public static AppColor[] getAppColors() {
        return appColors;
    }

    public static AppFontSize[] getAppFontSize() {
        return appFontSize;
    }

    public void setTimeColor(int redValue, int greenValue, int blueValue) {
        timeColor = new Color(redValue, greenValue, blueValue);
    }

    public void setTimeColor(Color color) {
        timeColor = color;
    }

    public void setTimeFont(String fontName) {
        timeFont = new Font(fontName, timeFont.getStyle(), timeFont.getSize());
        System.out.println("current font style is " + timeFont.getStyle());
    }

    public void setTimeFontSize(float size) {
        timeFont = timeFont.deriveFont(size);
    }

    public void setBackgroundColor(int redValue, int greenValue, int blueValue) {
        backgroundColor = new Color(redValue, greenValue, blueValue);
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getTimeColor() {
        return timeColor;
    }

    public Font getTimeFont() {
        return timeFont;
    }

    public float getTimeFontSize() {
        return (float) timeFont.getSize();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }
}
