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
    Frame frame;

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
        controlConstructorPanel = new ControlConstructorPanel(this);
        controlFieldPanel = new ControlFieldPanel(this, controlConstructorPanel);
        controlMethodPanel = new ControlMethodPanel(this, controlConstructorPanel);
        controlMemberPanel = new ControlMemberPanel(controlFieldPanel, controlMethodPanel, controlConstructorPanel);
        operationAreaDialogPanel = new OperationAreaDialogPanel(this, controlMemberPanel);
        declaredMemberListDialogPanel = new DeclaredMemberListDialogPanel(controlMemberPanel, operationAreaDialogPanel);
        declaredMemberListDialogPanel.setupJListMember(memberType.getTypeName());
        mainAreaDialogPanel.add(declaredMemberListDialogPanel, "declared_member_list_dialog_panel");
        this.add(operationAreaDialogPanel, BorderLayout.WEST);
        this.add(mainAreaDialogPanel, BorderLayout.CENTER);
    }

    private void initForPrimitive() {
        setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        setLayout(new BorderLayout());
        PanelInputText valueChangePanel = new PanelInputText("値");
        executeButton = new JButton("決定");
        System.out.println("memberType = " + memberType.getTypeName());
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
                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        char[] ch = valueChangePanel.getText().toCharArray();
                        if (ch.length > 1) {
                            JOptionPane.showMessageDialog(frame, "1文字以上入力できません");
                            return;
                        }
                        if (panel instanceof ControlConstructorPanel) {
                            ((ControlConstructorPanel)panel).getDataHolder().args[changeIndex] = (ch.length == 0 ? null : ch[0]);
                            ((ControlConstructorPanel)panel).updateCheckContainValueLabel(changeIndex);
                        } else if (panel instanceof ControlMethodPanel) {
                            ((ControlMethodPanel)panel).getDataHolder().args[changeIndex] = (ch.length == 0 ? null : ch[0]);
                            ((ControlMethodPanel)panel).updateCheckContainValueLabel(changeIndex);
                        } else if (panel instanceof ControlFieldPanel) {
                            ((ControlFieldPanel)panel).getDataHolder().value = (ch.length == 0 ? null : ch[0]);
                            ((ControlFieldPanel)panel).updateCheckContainValueLabel();
                        }
                        setVisible(false);
                    }
                });
                break;
            case "byte":
            case "java.lang.Byte":
                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if (panel instanceof ControlConstructorPanel) {
                                ((ControlConstructorPanel) panel).getDataHolder().args[changeIndex] = Byte.parseByte(valueChangePanel.getText());
                                ((ControlConstructorPanel) panel).updateCheckContainValueLabel(changeIndex);
                            } else if (panel instanceof ControlMethodPanel) {
                                ((ControlMethodPanel) panel).getDataHolder().args[changeIndex] = Byte.parseByte(valueChangePanel.getText());
                                ((ControlMethodPanel) panel).updateCheckContainValueLabel(changeIndex);
                            } else if (panel instanceof ControlFieldPanel) {
                                ((ControlFieldPanel) panel).getDataHolder().value = Byte.parseByte(valueChangePanel.getText());
                                ((ControlFieldPanel) panel).updateCheckContainValueLabel();
                            }
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frame, "整数を入力してください");
                            return;
                        }
                        setVisible(false);
                    }
                });
                break;
            case "short":
            case "java.lang.Short":
                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if (panel instanceof ControlConstructorPanel) {
                                ((ControlConstructorPanel) panel).getDataHolder().args[changeIndex] = Short.parseShort(valueChangePanel.getText());
                                ((ControlConstructorPanel) panel).updateCheckContainValueLabel(changeIndex);
                            } else if (panel instanceof ControlMethodPanel) {
                                ((ControlMethodPanel) panel).getDataHolder().args[changeIndex] = Short.parseShort(valueChangePanel.getText());
                                ((ControlMethodPanel) panel).updateCheckContainValueLabel(changeIndex);
                            } else if (panel instanceof ControlFieldPanel) {
                                ((ControlFieldPanel) panel).getDataHolder().value = Short.parseShort(valueChangePanel.getText());
                                ((ControlFieldPanel) panel).updateCheckContainValueLabel();
                            }
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frame, "整数を入力してください");
                            return;
                        }
                        setVisible(false);
                    }
                });
                break;
            case "int":
            case "java.lang.Integer":
                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if (panel instanceof ControlConstructorPanel) {
                                ((ControlConstructorPanel) panel).getDataHolder().args[changeIndex] = Integer.parseInt(valueChangePanel.getText());
                                ((ControlConstructorPanel) panel).updateCheckContainValueLabel(changeIndex);
                            } else if (panel instanceof ControlMethodPanel) {
                                ((ControlMethodPanel) panel).getDataHolder().args[changeIndex] = Integer.parseInt(valueChangePanel.getText());
                                ((ControlMethodPanel) panel).updateCheckContainValueLabel(changeIndex);
                            } else if (panel instanceof ControlFieldPanel) {
                                ((ControlFieldPanel) panel).getDataHolder().value = Integer.parseInt(valueChangePanel.getText());
                                ((ControlFieldPanel) panel).updateCheckContainValueLabel();
                            }
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frame, "整数を入力してください");
                            return;
                        }
                        setVisible(false);
                    }
                });
                break;
            case "long":
            case "java.lang.Long":
                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            if (panel instanceof ControlConstructorPanel) {
                                ((ControlConstructorPanel) panel).getDataHolder().args[changeIndex] = Long.parseLong(valueChangePanel.getText());
                                ((ControlConstructorPanel) panel).updateCheckContainValueLabel(changeIndex);
                            } else if (panel instanceof ControlMethodPanel) {
                                ((ControlMethodPanel) panel).getDataHolder().args[changeIndex] = Long.parseLong(valueChangePanel.getText());
                                ((ControlMethodPanel) panel).updateCheckContainValueLabel(changeIndex);
                            } else if (panel instanceof ControlFieldPanel) {
                                ((ControlFieldPanel) panel).getDataHolder().value = Long.parseLong(valueChangePanel.getText());
                                ((ControlFieldPanel) panel).updateCheckContainValueLabel();
                            }
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frame, "整数を入力してください");
                            return;
                        }
                        setVisible(false);
                    }
                });
                break;
            case "float":
            case "java.lang.Float":
                try {
                    executeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (panel instanceof ControlConstructorPanel) {
                                ((ControlConstructorPanel) panel).getDataHolder().args[changeIndex] = Float.parseFloat(valueChangePanel.getText());
                                ((ControlConstructorPanel) panel).updateCheckContainValueLabel(changeIndex);
                            } else if (panel instanceof ControlMethodPanel) {
                                ((ControlMethodPanel) panel).getDataHolder().args[changeIndex] = Float.parseFloat(valueChangePanel.getText());
                                ((ControlMethodPanel) panel).updateCheckContainValueLabel(changeIndex);
                            } else if (panel instanceof ControlFieldPanel) {
                                ((ControlFieldPanel) panel).getDataHolder().value = Float.parseFloat(valueChangePanel.getText());
                                ((ControlFieldPanel) panel).updateCheckContainValueLabel();
                            }
                            setVisible(false);
                        }
                    });
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "実数を入力してください");
                    return;
                }
                break;
            case "double":
            case "java.lang.Double":
                try {
                    executeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (panel instanceof ControlConstructorPanel) {
                                ((ControlConstructorPanel) panel).getDataHolder().args[changeIndex] = Double.parseDouble(valueChangePanel.getText());
                                ((ControlConstructorPanel) panel).updateCheckContainValueLabel(changeIndex);
                            } else if (panel instanceof ControlMethodPanel) {
                                ((ControlMethodPanel) panel).getDataHolder().args[changeIndex] = Double.parseDouble(valueChangePanel.getText());
                                ((ControlMethodPanel) panel).updateCheckContainValueLabel(changeIndex);
                            } else if (panel instanceof ControlFieldPanel) {
                                ((ControlFieldPanel) panel).getDataHolder().value = Double.parseDouble(valueChangePanel.getText());
                                ((ControlFieldPanel) panel).updateCheckContainValueLabel();
                            }
                            setVisible(false);
                        }
                    });
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "実数を入力してください");
                    return;
                }
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
