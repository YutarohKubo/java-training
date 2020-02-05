import javax.swing.*;
import java.awt.*;

public class PropertyDialog extends JDialog {

    public static final int DIALOG_WIDTH = 960;
    public static final int DIALOG_HEIGHT = 540;

    private Frame frame;
    private ControlConstructorPanel controlConstructorPanel;
    private ControlMethodPanel controlMethodPanel;
    private ControlFieldPanel controlFieldPanel;
    private MemberStateListPanel memberStateListPanel;
    private JPanel displayObjectPanel;
    private JLabel displayTitleLabel;
    private JLabel displayObjectLabel;

    public PropertyDialog (Frame frame, ControlConstructorPanel controlConstructorPanel, ControlMethodPanel controlMethodPanel, ControlFieldPanel controlFieldPanel) {
        super(frame, true);
        this.frame = frame;
        this.controlConstructorPanel = controlConstructorPanel;
        this.controlMethodPanel = controlMethodPanel;
        this.controlFieldPanel = controlFieldPanel;
        initLayout();
    }

    private void initLayout() {
        setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        setLayout(new BorderLayout());
        displayObjectPanel = new JPanel();
        displayObjectPanel.setLayout(new FlowLayout());
        displayTitleLabel = new JLabel("Now Instance : ");
        displayObjectLabel = new JLabel();
        if (controlConstructorPanel.getDataHolder() == null || controlConstructorPanel.getDataHolder().generatedObject == null) {
            displayObjectLabel.setText("null");
        } else {
            displayObjectLabel.setText(controlConstructorPanel.getDataHolder().generatedObject.getClass().getName());
        }
        displayObjectPanel.add(displayTitleLabel);
        displayObjectPanel.add(displayObjectLabel);
        memberStateListPanel = new MemberStateListPanel(controlConstructorPanel);
        memberStateListPanel.setupFieldInNowObject();
        this.add(displayObjectPanel, BorderLayout.NORTH);
        this.add(memberStateListPanel, BorderLayout.CENTER);
    }

}
