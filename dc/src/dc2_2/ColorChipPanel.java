package dc2_2;

import javax.swing.*;
import java.awt.*;

public class ColorChipPanel extends JPanel {
    private static int WIDTH = 20;
    private static int HEIGHT = 20;

    private Color backgroundColor;

    public ColorChipPanel(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        init();
    }

    private void init() {
        setBackground(backgroundColor);
        setSize(WIDTH, HEIGHT);
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        setBackground(backgroundColor);
    }
}
