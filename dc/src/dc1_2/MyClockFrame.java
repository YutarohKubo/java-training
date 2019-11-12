package dc1_2;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyClockFrame extends Frame {

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 300;

    private LocalDateTime currentTime;
    private String dateFormat = "yyyy-MM-dd";
    private String timeFormat = "HH:mm:ss";

    private Image bufferingImg;
    private Graphics buffer;

    private Property mProperty;

    private float prevFrameWidth = FRAME_WIDTH;
    private float prevFrameHeight = FRAME_HEIGHT;

    public MyClockFrame (String title) {
        super(title);
        init();
    }

    public Property getProperty() {
        return mProperty;
    }

    private void init () {
        mProperty = new Property();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setBackground(mProperty.getBackgroundColor());
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                //float ratio = (getWidth()/prevFrameWidth) * (getHeight()/prevFrameHeight);
                /*float ratio = (getWidth() * getHeight()) / (prevFrameWidth * prevFrameHeight);
                mProperty.setTimeFontSize(mProperty.getTimeFontSize()*ratio);
                prevFrameWidth = getWidth();
                prevFrameHeight = getHeight();
                System.out.println("hahaha");*/
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                super.componentMoved(e);
            }

            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
            }
        });

        MenuBar mMenuBar = new MenuBar();
        setMenuBar(mMenuBar);

        Menu menuDisplay = new Menu("Display");
        Menu menuSetting = new Menu("Setting");
        mMenuBar.add(menuDisplay);
        menuDisplay.add(menuSetting);

        MenuItem menuItemSettingTimeColor = new MenuItem("Time color");
        MenuItem menuItemSettingBackgroundColor = new MenuItem("Background color");
        MenuItem menuItemSettingFont = new MenuItem("Font");
        MenuItem menuItemSettingFontSize = new MenuItem("Font size");

        menuSetting.add(menuItemSettingTimeColor);
        menuSetting.add(menuItemSettingBackgroundColor);
        menuSetting.add(menuItemSettingFont);
        menuSetting.add(menuItemSettingFontSize);

        menuItemSettingTimeColor.addActionListener((e) -> {
            DialogClockSettingColor dialogSetting = new DialogClockSettingColor(this, "Time color");
            dialogSetting.setOnOkClickListener((dialog) -> {
                mProperty.setTimeColor(dialog.getRedValue(), dialog.getGreenValue(), dialog.getBlueValue());
                repaint();
            });
            dialogSetting.setBounds(FRAME_WIDTH/4, FRAME_HEIGHT/4, FRAME_WIDTH/2, FRAME_HEIGHT/2);
            dialogSetting.setVisible(true);
        });

        menuItemSettingBackgroundColor.addActionListener((e) -> {
            DialogClockSettingColor dialogSetting = new DialogClockSettingColor(this, "Background color");
            dialogSetting.setOnOkClickListener((dialog) -> {
                mProperty.setBackgroundColor(dialog.getRedValue(), dialog.getGreenValue(), dialog.getBlueValue());
                repaint();
            });
            dialogSetting.setBounds(FRAME_WIDTH/4, FRAME_HEIGHT/4, FRAME_WIDTH/2, FRAME_HEIGHT/2);
            dialogSetting.setVisible(true);
        });

        menuItemSettingFont.addActionListener((e) -> {
            DialogClockSettingFontName dialogSetting = new DialogClockSettingFontName(this, "Font");
            dialogSetting.setOnOkClickListener((dialog) -> {
                mProperty.setTimeFont(dialog.getSelectedFont());
                repaint();
            });
            dialogSetting.setBounds(FRAME_WIDTH/4, FRAME_HEIGHT/4, FRAME_WIDTH/2, FRAME_HEIGHT/2);
            dialogSetting.setVisible(true);
        });

        menuItemSettingFontSize.addActionListener((e) -> {
            DialogClockSettingFontSize dialogSetting = new DialogClockSettingFontSize(this, "Font size");
            dialogSetting.setOnOkClickListener((dialog) -> {
                mProperty.setTimeFontSize(dialog.getFontSize());
                repaint();
            });
            dialogSetting.setBounds(FRAME_WIDTH/4, FRAME_HEIGHT/4, FRAME_WIDTH/2, FRAME_HEIGHT/2);
            dialogSetting.setVisible(true);
        });


        Thread timerThread = new Thread(() -> {
            while (true) {
                currentTime = LocalDateTime.now();
                setBackground(mProperty.getBackgroundColor());
                //System.out.println("Font size " + mProperty.getTimeFontSize());
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
        double fontRatio = mProperty.getTimeFontSize()/Property.DEFAULT_FONT_SIZE;
        //System.out.println("fontRatio = " + fontRatio);
        setSize((int) (FRAME_WIDTH * fontRatio), (int) (FRAME_HEIGHT * fontRatio));
        bufferingImg = createImage(getWidth(), getHeight());
        buffer = bufferingImg.getGraphics();
        Font font = mProperty.getTimeFont();
        buffer.setFont(font);
        int stringDateWidth = buffer.getFontMetrics().stringWidth(currentTime.format(DateTimeFormatter.ofPattern(dateFormat))); //表示日付の文字列の幅を取得
        int stringDateHeight = buffer.getFontMetrics().getHeight(); //表示日付の文字列の高さを取得
        int stringTimeWidth = buffer.getFontMetrics().stringWidth(currentTime.format(DateTimeFormatter.ofPattern(timeFormat))); //表示時刻の文字列の幅を取得
        int stringTimeHeight = buffer.getFontMetrics().getHeight(); //表示時刻の文字列の高さを取得
        //System.out.println("stringDateWidth = " + stringDateWidth);
        buffer.setColor(mProperty.getTimeColor());
        buffer.drawString(currentTime.format(DateTimeFormatter.ofPattern(dateFormat)), getWidth()/2 - stringDateWidth/2,  60 * getHeight()/100 - stringDateHeight/2);
        buffer.drawString(currentTime.format(DateTimeFormatter.ofPattern(timeFormat)), getWidth()/2 - stringTimeWidth/2,  80 * getHeight()/100 - stringTimeHeight/2);
        g.drawImage(bufferingImg, 0, 0, this);
    }
}
