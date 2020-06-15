package dc2_3;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickablePanel extends JPanel implements MouseListener {

    private ClickListener listener;

    public ClickablePanel() {
        addMouseListener(this);
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
