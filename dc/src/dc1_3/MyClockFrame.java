package dc1_3;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyClockFrame extends Window implements MouseListener, MouseMotionListener {

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 300;

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
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setBackground(mProperty.getBackgroundColor());
        setAlwaysOnTop(true);
        addMouseListener(this);
        addMouseMotionListener(this);

        mPopupMenu = new PopupMenu();

        Menu menuSetting = new Menu("Setting");
        MenuItem menuItemCloseApp = new MenuItem("Close");
        menuItemCloseApp.addActionListener((e) -> {
            System.exit(0);
        });
        mPopupMenu.add(menuSetting);
        mPopupMenu.add(menuItemCloseApp);

        Menu menuSettingTimeColor = new Menu("Time color");
        Menu menuSettingBackgroundColor = new Menu("Background color");
        Menu menuSettingFont = new Menu("Font");
        Menu menuSettingFontSize = new Menu("Font size");

        final Property.AppColor[] colors = Property.getAppColors();
        MenuItem[] menuItemTimeColors = new MenuItem[colors.length];
        for (int i = 0; i < menuItemTimeColors.length; i++) {
            menuItemTimeColors[i] = new MenuItem(colors[i].getName());
            final int tmpI = i;
            menuItemTimeColors[i].addActionListener((e) -> {
                mProperty.setTimeColor(colors[tmpI].getColor());
            });
            menuSettingTimeColor.add(menuItemTimeColors[i]);//色設定メニューに、設定できる色の項目を格納
        }

        MenuItem[] menuItemBackgroundColors = new MenuItem[colors.length];
        for (int i = 0; i < menuItemBackgroundColors.length; i++) {
            menuItemBackgroundColors[i] = new MenuItem(colors[i].getName());
            final int tmpI = i;
            menuItemBackgroundColors[i].addActionListener((e) -> {
                mProperty.setBackgroundColor(colors[tmpI].getColor());
            });
            menuSettingBackgroundColor.add(menuItemBackgroundColors[i]);
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final String[] fontNames = ge.getAvailableFontFamilyNames();
        MenuItem[] menuItemFontNames = new MenuItem[fontNames.length];
        for (int i = 0; i < menuItemFontNames.length; i++) {
            menuItemFontNames[i] = new MenuItem(fontNames[i]);
            final int tmpI = i;
            menuItemFontNames[i].addActionListener((e) -> {
                mProperty.setTimeFont(fontNames[tmpI]);
            });
            menuSettingFont.add(menuItemFontNames[i]);
        }

        final Property.AppFontSize[] fontSize = Property.getAppFontSize();
        MenuItem[] menuItemFontSizes = new MenuItem[fontSize.length];
        for (int i = 0; i < menuItemFontSizes.length; i++) {
            menuItemFontSizes[i] = new MenuItem(fontSize[i].getName());
            final int tmpI = i;
            menuItemFontSizes[i].addActionListener((e) -> {
                mProperty.setTimeFontSize(fontSize[tmpI].getSize());
            });
            menuSettingFontSize.add(menuItemFontSizes[i]);
        }

        menuSetting.add(menuSettingTimeColor);
        menuSetting.add(menuSettingBackgroundColor);
        menuSetting.add(menuSettingFont);
        menuSetting.add(menuSettingFontSize);

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

    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        double fontRatio = mProperty.getTimeFontSize() / Property.DEFAULT_FONT_SIZE;
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
            setBounds(getX() + e.getXOnScreen() - mousePressXOnScreen, getY() + e.getYOnScreen() - mousePressYOnScreen, getWidth(), getHeight());
            mousePressXOnScreen = e.getXOnScreen();
            mousePressYOnScreen = e.getYOnScreen();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("mouse Moved on Window");
    }
}
