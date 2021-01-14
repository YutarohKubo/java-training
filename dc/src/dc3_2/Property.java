package dc3_2;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.*;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Property {

    public static final int DEFAULT_FRAME_WIDTH = 500;
    public static final int DEFAULT_FRAME_HEIGHT = 350;
    public static final int DEFAULT_DIALOG_WIDTH = 500;
    public static final int DEFAULT_DIALOG_HEIGHT = 300;
    public static final int LARGE_FONT_SIZE = 96;
    public static final int DEFAULT_FONT_SIZE = 48;
    public static final int SMALL_FONT_SIZE = 24;
    public static final String LARGE_FONT_SIZE_NAME = "Large";
    public static final String DEFAULT_FONT_SIZE_NAME = "Middle";
    public static final String SMALL_FONT_SIZE_NAME = "Small";
    private static final Color DEFAULT_BACKGROUND_COLOR = AppStyle.BLACK;

    public static final String[] fontFamilies = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    public static final AppColorData[] appColors = new AppColorData[]
            {
                    new AppColorData(AppStyle.VERT_PRAIRIE, "DEFAULT"),
                    new AppColorData(Color.BLACK, "BLACK"),
                    new AppColorData(Color.BLUE, "BLUE"),
                    new AppColorData(Color.CYAN, "CYAN"),
                    new AppColorData(Color.DARKGRAY, "DARK_GRAY"),
                    new AppColorData(Color.GRAY, "GRAY"),
                    new AppColorData(Color.GREEN, "GREEN"),
                    new AppColorData(Color.LIGHTGRAY, "LIGHT_GRAY"),
                    new AppColorData(Color.MAGENTA, "MAGENTA"),
                    new AppColorData(Color.ORANGE, "ORANGE"),
                    new AppColorData(Color.PINK, "PINK"),
                    new AppColorData(Color.RED, "RED"),
                    new AppColorData(Color.WHITE, "WHITE"),
                    new AppColorData(Color.YELLOW, "YELLOW"),
            };
    private static final AppFontSizeData[] appFontSize = new AppFontSizeData[]{
            new AppFontSizeData(LARGE_FONT_SIZE, "Large"),
            new AppFontSizeData(DEFAULT_FONT_SIZE, "Middle"),
            new AppFontSizeData(SMALL_FONT_SIZE, "Small"),
    };
    private static final String KEY_TIME_COLOR = "time_color";
    private static final String KEY_TIME_FONT = "time_font";
    private static final String KEY_TIME_FONT_SIZE_NAME = "time_font_size_name";
    private static final String KEY_BACKGROUND_COLOR = "background_color";
    private static final String KEY_WINDOW_X = "window_x";
    private static final String KEY_WINDOW_Y = "window_y";
    private static final String KEY_WINDOW_WIDTH = "window_width";
    private static final String KEY_WINDOW_HEIGHT = "window_height";

    // 各種設定値
    private AppColorData timeColor = appColors[0];
    private Font timeFont = Font.font(fontFamilies[0], FontWeight.NORMAL, DEFAULT_FONT_SIZE);
    private String timeFontFamily = fontFamilies[0];
    private AppFontSizeData timeFontSize = appFontSize[1];
    private AppColorData backgroundColor = appColors[1];
    private int windowX;
    private int windowY;
    private int windowWidth;
    private int windowHeight;

    private static Preferences prefs;

    public static AppColorData[] getAppColors() {
        return appColors;
    }

    public static AppFontSizeData[] getAppFontSize() {
        return appFontSize;
    }

    public void setTimeColor(String colorName) {
        for (AppColorData appColor : appColors) {
            if (appColor.getName().equals(colorName)) {
                timeColor = appColor;
                return;
            }
        }
        throw new IllegalArgumentException("Nothing such color");
    }

    public void setTimeFont(String fontName) {
        timeFont = Font.font(fontName, timeFont.getSize());
        timeFontFamily = fontName;
    }

    public void setTimeFontSize(float size) {
        timeFont = Font.font(timeFont.getName(), size);
        if (size <= SMALL_FONT_SIZE) {
            timeFontSize = appFontSize[2];
        } else if (size <= DEFAULT_FONT_SIZE) {
            timeFontSize = appFontSize[1];
        } else {
            timeFontSize = appFontSize[0];
        }
    }

    public void setTimeFontSize(String sizeName) {
        switch (sizeName) {
            case SMALL_FONT_SIZE_NAME:
                timeFont = Font.font(timeFont.getName(), SMALL_FONT_SIZE);
                timeFontSize = appFontSize[2];
                break;

            case DEFAULT_FONT_SIZE_NAME:
                timeFont = Font.font(timeFont.getName(), DEFAULT_FONT_SIZE);
                timeFontSize = appFontSize[1];
                break;

            case LARGE_FONT_SIZE_NAME:
                timeFont = Font.font(timeFont.getName(), LARGE_FONT_SIZE);
                timeFontSize = appFontSize[0];
                break;

            default:
                throw new IllegalArgumentException("Nothing such font name");
        }
    }

    public AppFontSizeData getTimeFontSize() {
        return timeFontSize;
    }

    public void setBackgroundColor(AppColorData backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setBackgroundColor(String colorName) {
        for (AppColorData appColor : appColors) {
            if (appColor.getName().equals(colorName)) {
                backgroundColor = appColor;
                return;
            }
        }
        throw new IllegalArgumentException("Nothing such color");
    }

    public AppColorData getTimeColor() {
        return timeColor;
    }

    public String getTimeColorName() {
        return timeColor.getName();
    }

    public Font getTimeFont() {
        return timeFont;
    }

    public String getTimeFontFamily() {
        return timeFontFamily;
    }

    public AppColorData getBackgroundColor() {
        return backgroundColor;
    }

    public String getBackgroundColorName() {
        return backgroundColor.getName();
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
        prefs.put(KEY_TIME_FONT, getTimeFontFamily());
        prefs.put(KEY_TIME_FONT_SIZE_NAME, getTimeFontSize().getName());
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
        setTimeFont(prefs.get(KEY_TIME_FONT, fontFamilies[0]));
        setTimeFontSize(prefs.get(KEY_TIME_FONT_SIZE_NAME, "Middle"));
        setBackgroundColor(prefs.get(KEY_BACKGROUND_COLOR, "BLACK"));
        windowX = prefs.getInt(KEY_WINDOW_X, 0);
        windowY = prefs.getInt(KEY_WINDOW_Y, 0);
        windowWidth = prefs.getInt(KEY_WINDOW_WIDTH, DEFAULT_FRAME_WIDTH);
        windowHeight = prefs.getInt(KEY_WINDOW_HEIGHT, DEFAULT_FRAME_HEIGHT);
    }
}
