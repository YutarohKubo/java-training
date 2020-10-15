package dc2_4;

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

        JMenuItem menuItemSetting = new JMenuItem("Setting");
        menuItemSetting.addActionListener((e) -> {
            System.out.println("current thread = " + Thread.currentThread());
            Main.displayThreadsAllList(Thread.currentThread().getThreadGroup());
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
        JMenuItem menuItemCloseApp = new JMenuItem("Close");
        menuItemCloseApp.addActionListener((e) -> {
            System.exit(0);
        });
        mPopupMenu.add(menuItemSetting);
        mPopupMenu.add(menuItemCloseApp);

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
