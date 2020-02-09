import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    public static final int FRAME_WIDTH = 1280;
    public static final int FRAME_HEIGHT = 720;

    private boolean isArrayPanelVisible;

    public AppFrame (String title) {
        super(title);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
    }

    public void setArrayPanelVisible(boolean arrayPanelVisible) {
        isArrayPanelVisible = arrayPanelVisible;
    }

    public boolean isArrayPanelVisible() {
        return isArrayPanelVisible;
    }
}
