import javax.swing.*;
import java.awt.*;

public class ConsoleAreaPanel extends InterpretPanel {

    private static JTextArea textArea;
    private static JScrollPane scrollPane;

    public ConsoleAreaPanel () {
        addComponent();
    }

    @Override
    void setPanelLayout() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
    }

    @Override
    void setupComponent() {
        textArea = new JTextArea(5, 60);
        scrollPane = new JScrollPane(textArea);
    }

    private void addComponent() {
        add(scrollPane, BorderLayout.CENTER);
    }

    public void reloadTextArea() {

    }

    public static void appendNewLog (String logText) {
        textArea.append(logText + "\n");
    }
}
