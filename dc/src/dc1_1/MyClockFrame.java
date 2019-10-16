package dc1_1;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyClockFrame extends Frame {

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 300;

    private LocalDateTime currentTime;
    private final String backgroundDate = "8888-88-88";
    private final String backgroundTime = "88:88:88";
    private String dateFormat = "yyyy-MM-dd";
    private String timeFormat = "HH:mm:ss";

    private Image bufferingImg;
    private Graphics buffer;

    public MyClockFrame (String title) {
        super(title);
        init();
    }

    private void init () {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setBackground(AppStyle.BLACK);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

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
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        bufferingImg = createImage(FRAME_WIDTH, FRAME_HEIGHT);
        buffer = bufferingImg.getGraphics();
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("fonts/7barSPBd.ttf"));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        font = font.deriveFont(48F);
        buffer.setFont(font);
        int stringDateWidth = buffer.getFontMetrics().stringWidth(currentTime.format(DateTimeFormatter.ofPattern(dateFormat))); //表示日付の文字列の幅を取得
        int stringDateHeight = buffer.getFontMetrics().getHeight(); //表示日付の文字列の高さを取得
        int stringTimeWidth = buffer.getFontMetrics().stringWidth(currentTime.format(DateTimeFormatter.ofPattern(timeFormat))); //表示時刻の文字列の幅を取得
        int stringTimeHeight = buffer.getFontMetrics().getHeight(); //表示時刻の文字列の高さを取得
        buffer.setColor(AppStyle.SUMI);
        buffer.drawString(backgroundDate, FRAME_WIDTH/2 - stringDateWidth/2,  40 * FRAME_HEIGHT/100 - stringDateHeight/2);
        buffer.drawString(backgroundTime, FRAME_WIDTH/2 - stringTimeWidth/2,  60 * FRAME_HEIGHT/100 - stringTimeHeight/2);
        buffer.setColor(AppStyle.VERT_PRAIRIE);
        buffer.drawString(currentTime.format(DateTimeFormatter.ofPattern(dateFormat)), FRAME_WIDTH/2 - stringDateWidth/2,  40 * FRAME_HEIGHT/100 - stringDateHeight/2);
        buffer.drawString(currentTime.format(DateTimeFormatter.ofPattern(timeFormat)), FRAME_WIDTH/2 - stringTimeWidth/2,  60 * FRAME_HEIGHT/100 - stringTimeHeight/2);
        g.drawImage(bufferingImg, 0, 0, this);
    }
}
