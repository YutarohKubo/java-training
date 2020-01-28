import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.*;

public class ChangeValueDialog extends JDialog {

    public static final int DIALOG_WIDTH = 960;
    public static final int DIALOG_HEIGHT = 540;

    java.lang.reflect.Type memberType;
    Object[] args;
    int changeIndex;
    JButton executeButton;

    public ChangeValueDialog(Frame frame, java.lang.reflect.Type type, Object[] args, int changeIndex) {
        super(frame, true);
        this.memberType = type;
        this.args = args;
        this.changeIndex = changeIndex;
        init();
    }

    private void init() {
        setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        setLayout(new BorderLayout());
        switch (memberType.getTypeName()) {
            case "java.lang.String":
                PanelInputText valueChangePanelString = new PanelInputText("値");
                executeButton = new JButton("実行");
                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        args[changeIndex] = valueChangePanelString.getText();
                        setVisible(false);
                    }
                });
                add(valueChangePanelString, BorderLayout.CENTER);
                add(executeButton, BorderLayout.SOUTH);
                break;
            case "boolean":
            case "java.lang.Boolean":
                PanelInputText valueChangePanelBoolean = new PanelInputText("値");
                executeButton = new JButton("実行");
                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        args[changeIndex] = Boolean.parseBoolean(valueChangePanelBoolean.getText());
                        setVisible(false);
                    }
                });
                add(valueChangePanelBoolean, BorderLayout.CENTER);
                add(executeButton, BorderLayout.SOUTH);
                break;
            case "char":
            case "java.lang.Character":
            case "byte":
            case "java.lang.Byte":
            case "short":
            case "java.lang.Short":
            case "int":
            case "java.lang.Integer":
            case "long":
            case "java.lang.Long":
            case "float":
            case "java.lang.Float":
            case "double":
            case "java.lang.Double":
                PanelInputText valueChangePanel = new PanelInputText("値");
                executeButton = new JButton("実行");
                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        setVisible(false);
                    }
                });
                add(valueChangePanel, BorderLayout.CENTER);
                add(executeButton, BorderLayout.SOUTH);
                break;
            default:
        }
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
