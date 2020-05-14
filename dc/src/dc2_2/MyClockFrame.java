package dc2_2;

import javax.swing.*;
import java.awt.event.*;

public class MyClockFrame extends JFrame implements MouseListener, MouseMotionListener, WindowListener {

    public static final int FRAME_WIDTH = 500;
    public static final int FRAME_HEIGHT = 300;

    private Property mProperty;
    private JMenuBar mMenuBar;

    public MyClockFrame (String title) {
        super(title);
        init();
    }

    private void init() {
        mProperty = new Property();
        mProperty.loadProperty();
        setBounds(mProperty.getWindowX(), mProperty.getWindowY(), mProperty.getWindowWidth(), mProperty.getWindowHeight());
        //setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        addMouseListener(this);
        addMouseMotionListener(this);
        addWindowListener(this);
        mMenuBar = new JMenuBar();
        setJMenuBar(mMenuBar);

        JMenu menuDisplay = new JMenu("Display");
        JMenuItem menuItemSetting = new JMenuItem("Setting");
        menuItemSetting.addActionListener((e) -> {
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

        mMenuBar.add(menuDisplay);
        menuDisplay.add(menuItemSetting);
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
        //System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("mouseReleased");
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
        //System.out.println("mouseDragged");
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
