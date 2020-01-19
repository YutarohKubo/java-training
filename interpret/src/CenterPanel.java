import java.awt.*;

public class CenterPanel extends InterpretPanel {

    public CenterPanel () {
        this.setOpaque(false);
    }

    @Override
    void setPanelLayout() {
        this.setLayout(new BorderLayout());
    }

    @Override
    void setupComponent() {
    }
}
