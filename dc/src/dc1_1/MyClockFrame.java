package dc1_1;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyClockFrame extends Frame {

    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 720;

    private LocalDateTime currentTime;

    public MyClockFrame (String title) {
        super(title);
        init();
    }

    private void init () {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
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
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timerThread.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString(currentTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")), 400, 300);
    }
}
