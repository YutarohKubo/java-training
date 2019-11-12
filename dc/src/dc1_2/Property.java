package dc1_2;

import java.awt.*;

public class Property {

    private static final int DEFAULT_FONT_STYLE = Font.PLAIN;
    public static final int DEFAULT_FONT_SIZE = 48;
    public static final int MAX_RGB_VALUE = 255;
    public static final int MAX_FONT_SIZE = 500;
    public static final Color DEFAULT_TIME_COLOR = AppStyle.VERT_PRAIRIE;
    private static final Font DEFAULT_TIME_FONT = AppStyle.TIMES_ROMAN;
    private static final Color DEFAULT_BACKGROUND_COLOR = AppStyle.BLACK;

    private Color timeColor = DEFAULT_TIME_COLOR;
    private Font timeFont = DEFAULT_TIME_FONT;
    private Color backgroundColor = DEFAULT_BACKGROUND_COLOR;

    public void setTimeColor (int redValue, int greenValue, int blueValue) {
        timeColor = new Color(redValue, greenValue, blueValue);
    }

    public void setTimeFont (String fontName) {
        timeFont = new Font(fontName, timeFont.getStyle(), timeFont.getSize());
    }

    public void setTimeFontSize (float size) {
        timeFont = timeFont.deriveFont(size);
    }

    public void setBackgroundColor (int redValue, int greenValue, int blueValue) {
        backgroundColor = new Color(redValue, greenValue, blueValue);
    }

    public Color getTimeColor() {
        return timeColor;
    }

    public Font getTimeFont() {
        return timeFont;
    }

    public float getTimeFontSize () {
        return (float) timeFont.getSize();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }
}
