package dc2_1;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static dc2_1.MyClockFrame.FRAME_HEIGHT;
import static dc2_1.MyClockFrame.FRAME_WIDTH;

public class MyClockPanel extends JPanel {

    private LocalDateTime currentTime;
    private String dateFormat = "yyyy-MM-dd";
    private String timeFormat = "HH:mm:ss";

    public MyClockPanel () {
        init();
    }

    private void init () {
        setBackground(AppStyle.BLACK);

        Thread timerThread = new Thread(() -> {
            while (true) {
                currentTime = LocalDateTime.now();
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
        Font font = new Font("TimesRoman", Font.PLAIN, 48);
        g.setFont(font);
        //font = font.deriveFont(48F);
        int stringDateWidth = g.getFontMetrics().stringWidth(currentTime.format(DateTimeFormatter.ofPattern(dateFormat))); //表示日付の文字列の幅を取得
        int stringDateHeight = g.getFontMetrics().getHeight(); //表示日付の文字列の高さを取得
        int stringTimeWidth = g.getFontMetrics().stringWidth(currentTime.format(DateTimeFormatter.ofPattern(timeFormat))); //表示時刻の文字列の幅を取得
        int stringTimeHeight = g.getFontMetrics().getHeight(); //表示時刻の文字列の高さを取得
        g.setColor(AppStyle.VERT_PRAIRIE);
        g.drawString(currentTime.format(DateTimeFormatter.ofPattern(dateFormat)), FRAME_WIDTH/2 - stringDateWidth/2,  40 * FRAME_HEIGHT/100 - stringDateHeight/2);
        g.drawString(currentTime.format(DateTimeFormatter.ofPattern(timeFormat)), FRAME_WIDTH/2 - stringTimeWidth/2,  60 * FRAME_HEIGHT/100 - stringTimeHeight/2);
    }

}
