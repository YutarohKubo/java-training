import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

public class ChangeValueDialog extends JDialog {

    public static final int DIALOG_WIDTH = 960;
    public static final int DIALOG_HEIGHT = 540;

    private static Map<String, String> mapType = new HashMap<String, String>() {
        {
            put("String", "java.lang.String");
            put("boolean", "boolean");
            put("Boolean", "java.lang.Boolean");
            put("char", "char");
            put("Character", "java.lang.Character");
            put("byte", "byte");
            put("Byte", "java.lang.Byte");
            put("short", "short");
            put("Short", "java.lang.Short");
            put("int", "int");
            put("Integer", "java.lang.Integer");
            put("long", "long");
            put("Long", "java.lang.Long");
            put("float", "float");
            put("Float", "java.lang.Float");
            put("double", "double");
            put("Double", "java.lang.Double");
        }
    };

    java.lang.reflect.Type memberType;
    //変更対象のパネルの種類 (Field, Method, Constructor)
    InterpretPanel panel;
    int changeIndex;
    JButton executeButton;

    MainAreaDialogPanel mainAreaDialogPanel;
    DeclaredMemberListDialogPanel declaredMemberListDialogPanel;
    OperationAreaDialogPanel operationAreaDialogPanel;
    ControlMemberPanel controlMemberPanel;
    ControlFieldPanel controlFieldPanel;
    ControlMethodPanel controlMethodPanel;
    ControlConstructorPanel controlConstructorPanel;

    public ChangeValueDialog(Frame frame, InterpretPanel panel, java.lang.reflect.Type type, int changeIndex) {
        super(frame, true);
        this.panel = panel;
        this.memberType = type;
        this.changeIndex = changeIndex;
        if (mapType.containsValue(type.getTypeName())) {
            initForPrimitive();
        } else {
            initNotForPrimitive();
        }
    }

    private void initNotForPrimitive() {
        setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        setLayout(new BorderLayout());
        mainAreaDialogPanel = new MainAreaDialogPanel();
        controlFieldPanel = new ControlFieldPanel(this);
        controlMethodPanel = new ControlMethodPanel(this);
        controlConstructorPanel = new ControlConstructorPanel(this);
        controlMemberPanel = new ControlMemberPanel(controlFieldPanel, controlMethodPanel, controlConstructorPanel);
        declaredMemberListDialogPanel = new DeclaredMemberListDialogPanel(controlMemberPanel);
        declaredMemberListDialogPanel.setupJListMember(memberType.getTypeName());
        operationAreaDialogPanel = new OperationAreaDialogPanel(this, controlMemberPanel);
        mainAreaDialogPanel.add(declaredMemberListDialogPanel, "declared_member_list_dialog_panel");
        this.add(operationAreaDialogPanel, BorderLayout.WEST);
        this.add(mainAreaDialogPanel, BorderLayout.CENTER);
    }

    private void initForPrimitive() {
        setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        setLayout(new BorderLayout());
        PanelInputText valueChangePanel = new PanelInputText("値");
        executeButton = new JButton("決定");
        switch (memberType.getTypeName()) {
            case "java.lang.String":
                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (panel instanceof ControlConstructorPanel) {
                            ((ControlConstructorPanel)panel).getDataHolder().args[changeIndex] = valueChangePanel.getText();
                            ((ControlConstructorPanel)panel).updateCheckContainValueLabel(changeIndex);
                        } else if (panel instanceof ControlMethodPanel) {
                            ((ControlMethodPanel)panel).getDataHolder().args[changeIndex] = valueChangePanel.getText();
                            ((ControlMethodPanel)panel).updateCheckContainValueLabel(changeIndex);
                        } else if (panel instanceof ControlFieldPanel) {
                            ((ControlFieldPanel)panel).getDataHolder().value = valueChangePanel.getText();
                            ((ControlFieldPanel)panel).updateCheckContainValueLabel();
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
                            ((ControlConstructorPanel)panel).getDataHolder().args[changeIndex] = Boolean.parseBoolean(valueChangePanel.getText());
                            ((ControlConstructorPanel)panel).updateCheckContainValueLabel(changeIndex);
                        } else if (panel instanceof ControlMethodPanel) {
                            ((ControlMethodPanel)panel).getDataHolder().args[changeIndex] = Boolean.parseBoolean(valueChangePanel.getText());
                            ((ControlMethodPanel)panel).updateCheckContainValueLabel(changeIndex);
                        } else if (panel instanceof ControlFieldPanel) {
                            ((ControlFieldPanel)panel).getDataHolder().value = Boolean.parseBoolean(valueChangePanel.getText());
                            ((ControlFieldPanel)panel).updateCheckContainValueLabel();
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
                            ((ControlConstructorPanel)panel).getDataHolder().args[changeIndex] = Integer.parseInt(valueChangePanel.getText());
                            ((ControlConstructorPanel)panel).updateCheckContainValueLabel(changeIndex);
                        } else if (panel instanceof ControlMethodPanel) {
                            ((ControlMethodPanel)panel).getDataHolder().args[changeIndex] = Integer.parseInt(valueChangePanel.getText());
                            ((ControlMethodPanel)panel).updateCheckContainValueLabel(changeIndex);
                        } else if (panel instanceof ControlFieldPanel) {
                            ((ControlFieldPanel)panel).getDataHolder().value = Integer.parseInt(valueChangePanel.getText());
                            ((ControlFieldPanel)panel).updateCheckContainValueLabel();
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
                            ((ControlConstructorPanel)panel).getDataHolder().args[changeIndex] = Double.parseDouble(valueChangePanel.getText());
                            ((ControlConstructorPanel)panel).updateCheckContainValueLabel(changeIndex);
                        } else if (panel instanceof ControlMethodPanel) {
                            ((ControlMethodPanel)panel).getDataHolder().args[changeIndex] = Double.parseDouble(valueChangePanel.getText());
                            ((ControlMethodPanel)panel).updateCheckContainValueLabel(changeIndex);
                        } else if (panel instanceof ControlFieldPanel) {
                            ((ControlFieldPanel)panel).getDataHolder().value = Double.parseDouble(valueChangePanel.getText());
                            ((ControlFieldPanel)panel).updateCheckContainValueLabel();
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
