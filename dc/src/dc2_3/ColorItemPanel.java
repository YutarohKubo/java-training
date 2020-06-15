package dc2_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ColorItemPanel extends ClickablePanel {

    private Color mouseFocusedColor = new Color(176, 196, 222);
    private Color defaultItemColor = new Color(231, 232, 226);

    private Color color;
    private String colorName;

    public ColorItemPanel(Color color, String colorName) {
        this.color = color;
        this.colorName = colorName;
        init();
    }

    private void init() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(defaultItemColor);
        ColorChipPanel colorChipPanel = new ColorChipPanel(color);
        add(colorChipPanel);
        add(new JLabel(colorName));
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
