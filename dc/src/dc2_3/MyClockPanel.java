package dc2_3;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyClockPanel extends JPanel {
    private static final int UPPER_BAR_HEIGHT = 52;

    private MyClockFrame frame;
    private LocalDateTime currentTime;
    private String dateFormat = "yyyy-MM-dd";
    private String timeFormat = "HH:mm:ss";

    public MyClockPanel (MyClockFrame frame) {
        this.frame = frame;
        init();
    }

    private void init () {
        setBackground(frame.getProperty().getBackgroundColor());

        Thread timerThread = new Thread(() -> {
            while (true) {
                currentTime = LocalDateTime.now();
                setBackground(frame.getProperty().getBackgroundColor());
                repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timerThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        double fontRatio = frame.getProperty().getTimeFontSize() / Property.DEFAULT_FONT_SIZE;
        frame.getProperty().setWindowWidth((int) (Property.DEFAULT_FRAME_WIDTH * fontRatio));
        frame.getProperty().setWindowHeight((int) (Property.DEFAULT_FRAME_HEIGHT * fontRatio));
        frame.setSize(frame.getProperty().getWindowWidth(), frame.getProperty().getWindowHeight());
        Font font = frame.getProperty().getTimeFont();
        g.setFont(font);
        //font = font.deriveFont(48F);
        int stringDateWidth = g.getFontMetrics().stringWidth(currentTime.format(DateTimeFormatter.ofPattern(dateFormat))); //表示日付の文字列の幅を取得
        int stringDateHeight = g.getFontMetrics().getHeight(); //表示日付の文字列の高さを取得
        int stringTimeWidth = g.getFontMetrics().stringWidth(currentTime.format(DateTimeFormatter.ofPattern(timeFormat))); //表示時刻の文字列の幅を取得
        int stringTimeHeight = g.getFontMetrics().getHeight(); //表示時刻の文字列の高さを取得
        g.setColor(frame.getProperty().getTimeColor());
        g.drawString(currentTime.format(DateTimeFormatter.ofPattern(dateFormat)), getWidth() / 2 - stringDateWidth / 2,  40 * (getHeight() + UPPER_BAR_HEIGHT) / 100 - stringDateHeight / 2);
        g.drawString(currentTime.format(DateTimeFormatter.ofPattern(timeFormat)), getWidth() / 2 - stringTimeWidth / 2,  60 * (getHeight() + UPPER_BAR_HEIGHT) / 100 - stringTimeHeight / 2);
    }

}
