import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;
import java.io.StringWriter;

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
        textArea = new JTextArea(8, 60);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
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

    public static void writeStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        pw.flush();
        appendNewLog(sw.toString());
    }
}
