package dc2_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ColorItemPanel extends JPanel implements MouseListener {

    private Color mouseFocusedColor = new Color(176, 196, 222);
    private Color defaultItemColor = new Color(231, 232, 226);

    private Color color;
    private String colorName;

    private ClickListener listener;

    public ColorItemPanel(Color color, String colorName) {
        this.color = color;
        this.colorName = colorName;
        addMouseListener(this);
        init();
    }

    private void init() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(defaultItemColor);
        ColorChipPanel colorChipPanel = new ColorChipPanel(color);
        add(colorChipPanel);
        add(new JLabel(colorName));
    }

    public void setOnClickListener(ClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        listener.onClick(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(mouseFocusedColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(defaultItemColor);
    }
}
