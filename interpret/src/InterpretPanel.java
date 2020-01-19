import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class InterpretPanel extends JPanel {

    public InterpretPanel () {
        setPanelLayout();
        setupComponent();
    }

    abstract void setPanelLayout();

    abstract void setupComponent();

    protected JPanel setMargin (Component target, int top, int left, int bottom, int right) {
        JPanel marginPanel = new JPanel();
        marginPanel.setOpaque(false);
        marginPanel.add(target);
        marginPanel.setBorder(new CompoundBorder(new EmptyBorder(top, left, bottom, right), null));
        return marginPanel;
    }

}
