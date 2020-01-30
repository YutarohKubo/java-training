import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.*;

public class ChangeValueDialog extends JDialog {

    public static final int DIALOG_WIDTH = 960;
    public static final int DIALOG_HEIGHT = 540;

    java.lang.reflect.Type memberType;
    InterpretPanel panel;
    int changeIndex;
    JButton executeButton;

    public ChangeValueDialog(Frame frame, InterpretPanel panel, java.lang.reflect.Type type, int changeIndex) {
        super(frame, true);
        this.panel = panel;
        this.memberType = type;
        this.changeIndex = changeIndex;
        init();
    }

    private void init() {
        setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        setLayout(new BorderLayout());
        PanelInputText valueChangePanel = new PanelInputText("値");
        executeButton = new JButton("実行");
        switch (memberType.getTypeName()) {
            case "java.lang.String":
                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (panel instanceof ControlConstructorPanel) {
                            ControlConstructorPanel.DataHolder.args[changeIndex] = valueChangePanel.getText();
                        } else if (panel instanceof ControlMethodPanel) {
                            ControlMethodPanel.DataHolder.args[changeIndex] = valueChangePanel.getText();
                        } else if (panel instanceof ControlFieldPanel) {
                            ControlFieldPanel.DataHolder.value = valueChangePanel.getText();
                        }
                        setVisible(false);
                    }
                });
                break;
            case "boolean":
            case "java.lang.Boolean":
                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (panel instanceof ControlConstructorPanel) {
                            ControlConstructorPanel.DataHolder.args[changeIndex] = Boolean.parseBoolean(valueChangePanel.getText());
                        } else if (panel instanceof ControlMethodPanel) {
                            ControlMethodPanel.DataHolder.args[changeIndex] = Boolean.parseBoolean(valueChangePanel.getText());
                        } else if (panel instanceof ControlFieldPanel) {
                            ControlFieldPanel.DataHolder.value = Boolean.parseBoolean(valueChangePanel.getText());
                        }
                        setVisible(false);
                    }
                });
                break;
            case "char":
            case "java.lang.Character":
            case "byte":
            case "java.lang.Byte":
            case "short":
            case "java.lang.Short":
            case "int":
            case "java.lang.Integer":
                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (panel instanceof ControlConstructorPanel) {
                            ControlConstructorPanel.DataHolder.args[changeIndex] = Integer.parseInt(valueChangePanel.getText());
                        } else if (panel instanceof ControlMethodPanel) {
                            ControlMethodPanel.DataHolder.args[changeIndex] = Integer.parseInt(valueChangePanel.getText());
                        } else if (panel instanceof ControlFieldPanel) {
                            ControlFieldPanel.DataHolder.value = Integer.parseInt(valueChangePanel.getText());
                        }
                        setVisible(false);
                    }
                });
            case "long":
            case "java.lang.Long":
            case "float":
            case "java.lang.Float":
            case "double":
            case "java.lang.Double":
                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (panel instanceof ControlConstructorPanel) {
                            ControlConstructorPanel.DataHolder.args[changeIndex] = Double.parseDouble(valueChangePanel.getText());
                        } else if (panel instanceof ControlMethodPanel) {
                            ControlMethodPanel.DataHolder.args[changeIndex] = Double.parseDouble(valueChangePanel.getText());
                        } else if (panel instanceof ControlFieldPanel) {
                            ControlFieldPanel.DataHolder.value = Double.parseDouble(valueChangePanel.getText());
                        }
                        setVisible(false);
                    }
                });
                break;
            default:
        }
        add(valueChangePanel, BorderLayout.CENTER);
        add(executeButton, BorderLayout.SOUTH);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
