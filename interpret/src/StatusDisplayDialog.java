import javax.swing.*;
import java.awt.*;

public class StatusDisplayDialog extends JDialog {

    public static final int DIALOG_WIDTH = 960;
    public static final int DIALOG_HEIGHT = 540;
    public static final int START_LOCATION_X = 160;
    public static final int START_LOCATION_Y = 90;

    private AppFrame frame;
    private DisplayInsideArrayPanel displayInsideArrayPanel;
    private ControlConstructorPanel controlConstructorPanel;
    private ControlMethodPanel controlMethodPanel;
    private ControlFieldPanel controlFieldPanel;
    private MemberStateListPanel memberStateListPanel;
    private JPanel displayObjectPanel;
    private JLabel displayTitleLabel;
    private JLabel displayObjectLabel;

    public StatusDisplayDialog(AppFrame frame, DisplayInsideArrayPanel displayInsideArrayPanel, ControlConstructorPanel controlConstructorPanel, ControlMethodPanel controlMethodPanel, ControlFieldPanel controlFieldPanel) {
        super(frame, true);
        this.frame = frame;
        this.displayInsideArrayPanel = displayInsideArrayPanel;
        this.controlConstructorPanel = controlConstructorPanel;
        this.controlMethodPanel = controlMethodPanel;
        this.controlFieldPanel = controlFieldPanel;
        initLayout();
    }

    private void initLayout() {
        setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        setLayout(new BorderLayout());
        setLocation(frame.getX() + START_LOCATION_X, frame.getY() + START_LOCATION_Y);
        displayObjectPanel = new JPanel();
        displayObjectPanel.setLayout(new FlowLayout());
        displayTitleLabel = new JLabel("Now Instance : ");
        displayObjectLabel = new JLabel();
        if (frame.isArrayPanelVisible()) {
            if (displayInsideArrayPanel.getArrayElement() == null){
                displayObjectLabel.setText("null");
            } else {
                displayObjectLabel.setText(displayInsideArrayPanel.getArrayElement().getClass().getName());
                memberStateListPanel = new MemberStateListPanel(frame, displayInsideArrayPanel, controlConstructorPanel);
                memberStateListPanel.setupFieldInNowObject(displayInsideArrayPanel.getArrayElement().getClass());
            }
        } else {
            if (controlConstructorPanel.getDataHolder() == null || controlConstructorPanel.getDataHolder().generatedObject == null) {
                displayObjectLabel.setText("null");
            } else {
                displayObjectLabel.setText(controlConstructorPanel.getDataHolder().generatedObject.getClass().getName());
                memberStateListPanel = new MemberStateListPanel(frame, displayInsideArrayPanel, controlConstructorPanel);
                memberStateListPanel.setupFieldInNowObject(controlConstructorPanel.getDataHolder().generatedObject.getClass());
            }
        }
        displayObjectPanel.add(displayTitleLabel);
        displayObjectPanel.add(displayObjectLabel);
        this.add(displayObjectPanel, BorderLayout.NORTH);
        if (memberStateListPanel != null) {
            this.add(memberStateListPanel, BorderLayout.CENTER);
        }
    }

}
