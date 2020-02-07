import java.awt.*;

public class MainAreaPanel extends InterpretPanel {

    private DisplayInsideArrayPanel displayInsideArrayPanel;

    public MainAreaPanel (DisplayInsideArrayPanel displayInsideArrayPanel) {
        this.displayInsideArrayPanel = displayInsideArrayPanel;
    }

    @Override
    void setPanelLayout() {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
    }

    @Override
    void setupComponent() {

    }

    public void showArrayPanel() {
        this.add(displayInsideArrayPanel, BorderLayout.EAST);
        this.revalidate();
    }

    public void makeArray(int length) {
        displayInsideArrayPanel.makeList(length);
    }

    public void removeArrayPanel() {
        this.remove(displayInsideArrayPanel);
        this.revalidate();
    }

}
