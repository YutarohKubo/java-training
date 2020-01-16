package dc1_4;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.prefs.Preferences;

public class MyClockFrame extends Window implements MouseListener, MouseMotionListener {

    private LocalDateTime currentTime;
    private String dateFormat = "yyyy-MM-dd";
    private String timeFormat = "HH:mm:ss";

    private Image bufferingImg;
    private Graphics buffer;

    private Property mProperty;
    private PopupMenu mPopupMenu;

    private int mousePressXOnScreen;
    private int mousePressYOnScreen;

    private boolean isEnabledDrag;

    public MyClockFrame() {
        super(new Frame());
        init();
    }

    private void init() {
        mProperty = new Property();
        mProperty.loadProperty();
        setBounds(mProperty.getWindowX(), mProperty.getWindowY(), mProperty.getWindowWidth(), mProperty.getWindowHeight());
        setBackground(mProperty.getBackgroundColor());
        setAlwaysOnTop(true);
        addMouseListener(this);
        addMouseMotionListener(this);

        mPopupMenu = new PopupMenu();

        MenuItem menuSetting = new MenuItem("Setting");
        MenuItem menuItemCloseApp = new MenuItem("Close");
        menuSetting.addActionListener((e) -> {
            PropertyDialog propertyDialog = new PropertyDialog(MyClockFrame.this, "Setting");
            propertyDialog.setOnOkClickListener((dialog) -> {
                mProperty.setTimeFont(propertyDialog.getChoosedFont());
                mProperty.setTimeColor(propertyDialog.getChoosedTimeColor());
                mProperty.setTimeFontSize(propertyDialog.getChoosedFontSize());
                mProperty.setBackgroundColor(propertyDialog.getChoosedBackgroundColor());
                mProperty.saveProperty();
                propertyDialog.setVisible(false);
            });
            if (mProperty.getTimeFontSizeName().equals(Property.LARGE_FONT_SIZE_NAME)) {
                propertyDialog.setBounds(mProperty.getWindowX() + mProperty.getWindowWidth() / 8, mProperty.getWindowY() + mProperty.getWindowHeight() / 8, mProperty.getWindowWidth() * 3 / 4, mProperty.getWindowHeight() * 3 / 4);
            } else {
                propertyDialog.setBounds(mProperty.getWindowX() - Property.DEFAULT_FRAME_WIDTH * 2 / 8, mProperty.getWindowY() - Property.DEFAULT_FRAME_HEIGHT * 2 / 8, Property.DEFAULT_FRAME_WIDTH * 6 / 4, Property.DEFAULT_FRAME_HEIGHT * 6 / 4);
            }
            propertyDialog.setVisible(true);
        });
        menuItemCloseApp.addActionListener((e) -> {
            mProperty.saveProperty();
            System.exit(0);
        });
        mPopupMenu.add(menuSetting);
        mPopupMenu.add(menuItemCloseApp);

        add(mPopupMenu);

        Thread timerThread = new Thread(() -> {
            while (true) {
                currentTime = LocalDateTime.now();
                setBackground(mProperty.getBackgroundColor());
                //System.out.println("Font size " + mProperty.getTimeFontSize());
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        timerThread.start();
    }

    public Property getProperty() {
        return mProperty;
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        double fontRatio = mProperty.getTimeFontSize() / Property.DEFAULT_FONT_SIZE;
        //System.out.println("fontRatio = " + fontRatio);
        mProperty.setWindowWidth((int) (Property.DEFAULT_FRAME_WIDTH * fontRatio));
        mProperty.setWindowHeight((int) (Property.DEFAULT_FRAME_HEIGHT * fontRatio));
        setSize(mProperty.getWindowWidth(), mProperty.getWindowHeight());
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
        buffer.drawString(currentTime.format(DateTimeFormatter.ofPattern(dateFormat)), getWidth() / 2 - stringDateWidth / 2, 60 * getHeight() / 100 - stringDateHeight / 2);
        buffer.drawString(currentTime.format(DateTimeFormatter.ofPattern(timeFormat)), getWidth() / 2 - stringTimeWidth / 2, 80 * getHeight() / 100 - stringTimeHeight / 2);
        g.drawImage(bufferingImg, 0, 0, this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("Click on Window");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Press on Window: " + "eventMousePressed X=" + e.getX() + " Y=" + e.getY() + " Mouse Kind=" + e.getButton());
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                //System.out.println(getX() + " : " + getY());
                mousePressXOnScreen = e.getXOnScreen();
                mousePressYOnScreen = e.getYOnScreen();
                isEnabledDrag = true;
                break;
            case MouseEvent.BUTTON3:
                mPopupMenu.show(this, e.getX(), e.getY());
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("Release on Window");
        mProperty.saveProperty();
        isEnabledDrag = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("Enter on Window");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("mouse Exited on Window");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //System.out.println("mouse Dragged on Window: " + "diffX = " + (e.getXOnScreen() - mousePressXOnScreen) + " diffY = " + (e.getYOnScreen() - mousePressYOnScreen) + " Mouse Kind=" + e.getButton());
        //System.out.println("e.getX() = " + e.getX() + " e.getY() = " + e.getY());
        if (isEnabledDrag) {
            mProperty.setWindowX(getX() + e.getXOnScreen() - mousePressXOnScreen);
            mProperty.setWindowY(getY() + e.getYOnScreen() - mousePressYOnScreen);
            setBounds(mProperty.getWindowX(), mProperty.getWindowY(), getWidth(), getHeight());
            mousePressXOnScreen = e.getXOnScreen();
            mousePressYOnScreen = e.getYOnScreen();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("mouse Moved on Window");
    }
}
