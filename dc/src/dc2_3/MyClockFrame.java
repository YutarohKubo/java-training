package dc2_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyClockFrame extends JWindow implements MouseListener, MouseMotionListener, WindowListener {

    private Property mProperty;
    private JPopupMenu mPopupMenu;

    private int mousePressXOnScreen;
    private int mousePressYOnScreen;
    private boolean isEnabledDrag;

    public MyClockFrame () {
        super(new Frame());
        init();
    }

    private void init() {
        mProperty = new Property();
        mProperty.loadProperty();
        setBounds(mProperty.getWindowX(), mProperty.getWindowY(), mProperty.getWindowWidth(), mProperty.getWindowHeight());
        setAlwaysOnTop(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addWindowListener(this);

        mPopupMenu = new JPopupMenu();

        JMenu menuSetting = new JMenu("Setting");
        JMenuItem menuItemCloseApp = new JMenuItem("Close");
        menuItemCloseApp.addActionListener((e) -> {
            System.exit(0);
        });
        mPopupMenu.add(menuSetting);
        mPopupMenu.add(menuItemCloseApp);

        JMenu menuSettingTimeColor = new JMenu("Time color");
        JMenu menuSettingBackgroundColor = new JMenu("Background color");
        JMenu menuSettingFont = new JMenu("Font");
        MenuScroller menuSettingFontScroller = MenuScroller.setScrollerFor(menuSettingFont);
        JMenu menuSettingFontSize = new JMenu("Font size");

        final Property.AppColor[] colors = Property.getAppColors();
        ColorItemPanel[] menuItemTimeColors = new ColorItemPanel[colors.length];
        for (int i = 0; i < menuItemTimeColors.length; i++) {
            menuItemTimeColors[i] = new ColorItemPanel(colors[i].getColor(), colors[i].getName());
            final int tmpI = i;
            menuItemTimeColors[i].setOnClickListener((e) -> {
                mProperty.setTimeColor(colors[tmpI].getColor());
                mPopupMenu.setVisible(false);
            });
            menuSettingTimeColor.add(menuItemTimeColors[i]);//色設定メニューに、設定できる色の項目を格納
        }

        ColorItemPanel[] menuItemBackgroundColors = new ColorItemPanel[colors.length];
        for (int i = 0; i < menuItemBackgroundColors.length; i++) {
            menuItemBackgroundColors[i] = new ColorItemPanel(colors[i].getColor(), colors[i].getName());
            final int tmpI = i;
            menuItemBackgroundColors[i].setOnClickListener((e) -> {
                mProperty.setBackgroundColor(colors[tmpI].getColor());
                mPopupMenu.setVisible(false);
            });
            menuSettingBackgroundColor.add(menuItemBackgroundColors[i]);
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final String[] fontNames = ge.getAvailableFontFamilyNames();
        JMenuItem[] menuItemFontNames = new JMenuItem[fontNames.length];
        for (int i = 0; i < menuItemFontNames.length; i++) {
            menuItemFontNames[i] = new JMenuItem(fontNames[i]);
            final int tmpI = i;
            menuItemFontNames[i].addActionListener((e) -> {
                mProperty.setTimeFont(fontNames[tmpI]);
            });
            menuSettingFont.add(menuItemFontNames[i]);
        }

        final Property.AppFontSize[] fontSize = Property.getAppFontSize();
        JMenuItem[] menuItemFontSizes = new JMenuItem[fontSize.length];
        for (int i = 0; i < menuItemFontSizes.length; i++) {
            menuItemFontSizes[i] = new JMenuItem(fontSize[i].getName());
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
    }

    public Property getProperty() {
        return mProperty;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("mouseClicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
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
        //System.out.println("mouseReleased");
        isEnabledDrag = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("mouseExited");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isEnabledDrag) {
            setBounds(getX() + e.getXOnScreen() - mousePressXOnScreen, getY() + e.getYOnScreen() - mousePressYOnScreen, getWidth(), getHeight());
            mousePressXOnScreen = e.getXOnScreen();
            mousePressYOnScreen = e.getYOnScreen();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mProperty.setWindowX(getX());
        mProperty.setWindowY(getY());
        mProperty.saveProperty();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        mProperty.setWindowX(getX());
        mProperty.setWindowY(getY());
        mProperty.saveProperty();
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
