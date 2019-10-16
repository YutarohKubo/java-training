package dc1_2;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyClockFrame extends Frame {

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 250;

    private Image bufferingImg;
    private Graphics buffer;
    private LocalDateTime currentTime;
    private String timeFormat = "yyyy/MM/dd HH:mm:ss";

    public MyClockFrame (String title) {
        super(title);
        init();
    }

    private void init () {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLayout(new BorderLayout());
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
        buffer.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        int stringTimeWidth = buffer.getFontMetrics().stringWidth(currentTime.format(DateTimeFormatter.ofPattern(timeFormat))); //表示時刻の文字列の幅を取得
        buffer.drawString(currentTime.format(DateTimeFormatter.ofPattern(timeFormat)), FRAME_WIDTH/2 - stringTimeWidth/2,  FRAME_HEIGHT/2);

        g.drawImage(bufferingImg, 0, 0, this);
    }
}
