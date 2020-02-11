import javax.swing.*;
import java.awt.*;

public class DeclaredAllMemberDialog extends JDialog {

    public static final int DIALOG_WIDTH = 960;
    public static final int DIALOG_HEIGHT = 540;

    private JTextArea displayAllMemberTextArea;
    private JScrollPane scrollPane;

    public DeclaredAllMemberDialog (Frame frame) {
        super(frame, "完全な宣言", true);
        setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        setLayout(new BorderLayout());
        displayAllMemberTextArea = new JTextArea(10, 60);
        scrollPane = new JScrollPane(displayAllMemberTextArea);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void appendTextToTextArea(String text) {
        displayAllMemberTextArea.append(text);
    }

    public void scrollTop() {

    }

}
