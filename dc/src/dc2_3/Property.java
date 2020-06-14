package dc2_3;

import java.awt.*;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Property {

    public static final int DEFAULT_FRAME_WIDTH = 500;
    public static final int DEFAULT_FRAME_HEIGHT = 300;
    private static final int DEFAULT_FONT_STYLE = Font.PLAIN;
    public static final int LARGE_FONT_SIZE = 96;
    public static final int DEFAULT_FONT_SIZE = 48;
    public static final int SMALL_FONT_SIZE = 24;
    public static final String LARGE_FONT_SIZE_NAME = "Large";
    public static final String DEFAULT_FONT_SIZE_NAME = "Middle";
    public static final String SMALL_FONT_SIZE_NAME = "Small";
    public static final int MAX_RGB_VALUE = 255;
    public static final int MAX_FONT_SIZE = 500;
    public static final Color DEFAULT_TIME_COLOR = AppStyle.VERT_PRAIRIE;
    private static final Font DEFAULT_TIME_FONT = AppStyle.TIMES_ROMAN;
    private static final Color DEFAULT_BACKGROUND_COLOR = AppStyle.BLACK;

    public static final AppColor[] appColors = new AppColor[]
            {
                    new AppColor(AppStyle.VERT_PRAIRIE, "DEFAULT"),
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
                    new AppColor(Color.YELLOW, "YELLOW"),
            };
    private static final AppFontSize[] appFontSize = new AppFontSize[]{
            new AppFontSize(LARGE_FONT_SIZE, "Large"),
            new AppFontSize(DEFAULT_FONT_SIZE, "Middle"),
            new AppFontSize(SMALL_FONT_SIZE, "Small"),
    };
    private static final String KEY_TIME_COLOR = "time_color";
    private static final String KEY_TIME_FONT = "time_font";
    private static final String KEY_TIME_FONT_SIZE_NAME = "time_font_size_name";
    private static final String KEY_BACKGROUND_COLOR = "background_color";
    private static final String KEY_WINDOW_X = "window_x";
    private static final String KEY_WINDOW_Y = "window_y";
    private static final String KEY_WINDOW_WIDTH = "window_width";
    private static final String KEY_WINDOW_HEIGHT = "window_height";
    private Color timeColor = DEFAULT_TIME_COLOR;
    private Font timeFont = DEFAULT_TIME_FONT;
    private String timeFontSizeName = DEFAULT_FONT_SIZE_NAME;
    private Color backgroundColor = DEFAULT_BACKGROUND_COLOR;
    private int windowX;
    private int windowY;
    private int windowWidth;
    private int windowHeight;

    private static Preferences prefs;

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

    public void setTimeColor(String colorName) {
        for (AppColor appColor : appColors) {
            if (appColor.name.equals(colorName)) {
                timeColor = appColor.color;
                return;
            }
        }
        throw new IllegalArgumentException("Nothing such color");
    }

    public void setTimeFont(String fontName) {
        timeFont = new Font(fontName, timeFont.getStyle(), timeFont.getSize());
    }

    public void setTimeFontSize(float size) {
        timeFont = timeFont.deriveFont(size);
        if (size <= SMALL_FONT_SIZE) {
            timeFontSizeName = SMALL_FONT_SIZE_NAME;
        } else if (size <= DEFAULT_FONT_SIZE) {
            timeFontSizeName = DEFAULT_FONT_SIZE_NAME;
        } else {
            timeFontSizeName = LARGE_FONT_SIZE_NAME;
        }
    }

    public void setTimeFontSize(String sizeName) {
        switch (sizeName) {
            case SMALL_FONT_SIZE_NAME:
                timeFont = timeFont.deriveFont((float) SMALL_FONT_SIZE);
                break;

            case DEFAULT_FONT_SIZE_NAME:
                timeFont = timeFont.deriveFont((float) DEFAULT_FONT_SIZE);
                break;

            case LARGE_FONT_SIZE_NAME:
                timeFont = timeFont.deriveFont((float) LARGE_FONT_SIZE);
                break;

            default:
                throw new IllegalArgumentException("Nothing such font name");
        }
        timeFontSizeName = sizeName;
    }

    public String getTimeFontSizeName() {
        return timeFontSizeName;
    }

    public void setBackgroundColor(int redValue, int greenValue, int blueValue) {
        backgroundColor = new Color(redValue, greenValue, blueValue);
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setBackgroundColor(String colorName) {
        for (AppColor appColor : appColors) {
            if (appColor.name.equals(colorName)) {
                backgroundColor = appColor.color;
                return;
            }
        }
        throw new IllegalArgumentException("Nothing such color");
    }

    public Color getTimeColor() {
        return timeColor;
    }

    public String getTimeColorName() {
        for (AppColor appColor : appColors) {
            if (appColor.color.equals(timeColor)) {
                return appColor.getName();
            }
        }
        throw new IllegalArgumentException("Nothing such color");
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

    public String getBackgroundColorName() {
        for (AppColor appColor : appColors) {
            if (appColor.color.equals(backgroundColor)) {
                return appColor.getName();
            }
        }
        throw new IllegalArgumentException("Nothing such color");
    }

    public int getWindowX() {
        return windowX;
    }

    public void setWindowX(int windowX) {
        this.windowX = windowX;
    }

    public int getWindowY() {
        return windowY;
    }

    public void setWindowY(int windowY) {
        this.windowY = windowY;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public void saveProperty () {
        if (prefs == null) {
            prefs = Preferences.userNodeForPackage(Main.class);
        }
        prefs.put(KEY_TIME_COLOR, getTimeColorName());
        prefs.put(KEY_TIME_FONT, getTimeFont().getName());
        prefs.put(KEY_TIME_FONT_SIZE_NAME, getTimeFontSizeName());
        prefs.put(KEY_BACKGROUND_COLOR, getBackgroundColorName());
        prefs.putInt(KEY_WINDOW_X, windowX);
        prefs.putInt(KEY_WINDOW_Y, windowY);
        prefs.putInt(KEY_WINDOW_WIDTH, windowWidth);
        prefs.putInt(KEY_WINDOW_HEIGHT, windowHeight);
        try {
            prefs.flush();
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    public void loadProperty () {
        if (prefs == null) {
            prefs = Preferences.userNodeForPackage(Main.class);
        }
        setTimeColor(prefs.get(KEY_TIME_COLOR, "DEFAULT"));
        setTimeFont(prefs.get(KEY_TIME_FONT, "SansSerif"));
        setTimeFontSize(prefs.get(KEY_TIME_FONT_SIZE_NAME, "Middle"));
        setBackgroundColor(prefs.get(KEY_BACKGROUND_COLOR, "BLACK"));
        windowX = prefs.getInt(KEY_WINDOW_X, 0);
        windowY = prefs.getInt(KEY_WINDOW_Y, 0);
        windowWidth = prefs.getInt(KEY_WINDOW_WIDTH, DEFAULT_FRAME_WIDTH);
        windowHeight = prefs.getInt(KEY_WINDOW_HEIGHT, DEFAULT_FRAME_HEIGHT);
    }
}
