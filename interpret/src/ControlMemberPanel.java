import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.*;

public class ControlMemberPanel extends InterpretPanel {

    private AppFrame appFrame;
    private PanelInputText inputToMemberPanel;
    private JPanel executeButtonPanel;
    private JButton executeChangeValueButton;
    private JButton[] methodArgmentButtons;
    private JButton[] constructorArgmentButtons;
    private JButton executeButton;

    private MemberData targetMemberData;
    private Object generatedObject;

    //Constructor targetConstructor;
    Method targetMethod;
    Field targetField;

    public enum Mode {
        FIELD,
        METHOD,
        CONSTRUCTOR,
    }

    public ControlMemberPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
        addComponent();
    }

    @Override
    void setPanelLayout() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    @Override
    void setupComponent() {
        inputToMemberPanel = new PanelInputText("");
        initExecuteButtonPanel();
        executeChangeValueButton = new JButton("実行");
        executeChangeValueButton.setAlignmentX(0.5f);
        executeChangeValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        /*executeMethodButton = new JButton("実行");
        executeMethodButton.setAlignmentX(0.5f);
        executeMethodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (targetMemberData.getMember() instanceof Method) {
                    Method targetMethod = (Method) targetMemberData.getMember();
                    String[] argsStr = inputToMemberPanel.getText().split(" +");
                    Object[] args = new Object[argsStr.length];
                    for (int i = 0; i < argsStr.length; i++) {
                        switch (targetMethod.getGenericParameterTypes()[i].getTypeName()) {
                            case "boolean":
                            case "java.lang.Boolean":
                                args[i] = Boolean.parseBoolean(argsStr[i]);
                                break;
                        }
                    }
                    try {
                        targetMethod.invoke(generatedObject, args);
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });*/
        /*generateConstructorButton = new JButton("生成");
        generateConstructorButton.setAlignmentX(0.5f);
        generateConstructorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (targetMemberData.getMember() instanceof Constructor) {
                    Constructor targetConstructor = (Constructor) targetMemberData.getMember();
                    String[] argsStr = inputToMemberPanel.getText().split(" +");
                    Object[] args = new Object[argsStr.length];
                    for (int i = 0; i < argsStr.length; i++) {
                        switch (targetConstructor.getGenericParameterTypes()[i].getTypeName()) {
                            case "java.lang.String":
                                args[i] = argsStr[i];
                                break;
                        }
                    }
                    try {
                        generatedObject = targetConstructor.newInstance(args);
                    } catch (InstantiationException ex) {
                        ex.printStackTrace();
                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    } catch (InvocationTargetException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });*/
    }

    private void addComponent() {
        setComponentMode(Mode.FIELD);
    }

    private void initExecuteButtonPanel() {
        executeButtonPanel = new JPanel();
        executeButtonPanel.setLayout(new BoxLayout(executeButtonPanel, BoxLayout.PAGE_AXIS));
        executeButton = new JButton("実行");
    }

    public void setComponentMode(Mode mode) {
        this.remove(executeButtonPanel);
        initExecuteButtonPanel();
        switch (mode) {
            case FIELD:
                executeChangeValueButton = new JButton("変更");
                executeChangeValueButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                executeChangeValueButton.setAlignmentX(0.5f);
                executeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
                executeButton.setAlignmentX(0.5f);
                executeButtonPanel.add(executeChangeValueButton);
                executeButtonPanel.add(executeButton);
                this.add(executeButtonPanel);
                revalidate();
                break;
            case METHOD:
                if (targetMemberData.getMember() instanceof Method) {
                    Method targetMethod = (Method) targetMemberData.getMember();
                    Type[] argTypes = targetMethod.getGenericParameterTypes();
                    methodArgmentButtons = new JButton[argTypes.length];
                    Object[] args = new Object[argTypes.length];
                    for (int i = 0; i < argTypes.length; i++) {
                        args[i] = new Object();
                    }
                    for (int i = 0; i < argTypes.length; i++) {
                        methodArgmentButtons[i] = new JButton("引数" + i);
                        int finalI = i;
                        methodArgmentButtons[i].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ChangeValueDialog dialog = new ChangeValueDialog(appFrame, argTypes[finalI], args, finalI);
                                dialog.setVisible(true);
                            }
                        });
                        executeButtonPanel.add(methodArgmentButtons[i]);
                        methodArgmentButtons[i].setAlignmentX(0.5f);
                    }
                    executeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                targetMethod.invoke(generatedObject, args);
                            } catch (IllegalAccessException ex) {
                                ex.printStackTrace();
                            } catch (InvocationTargetException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });

                }
                executeButton.setAlignmentX(0.5f);
                executeButtonPanel.add(executeButton);
                this.add(executeButtonPanel);
                revalidate();
                break;
            case CONSTRUCTOR:
                if (targetMemberData.getMember() instanceof Constructor) {
                    Constructor targetConstructor = (Constructor) targetMemberData.getMember();
                    Type[] argTypes = targetConstructor.getGenericParameterTypes();
                    constructorArgmentButtons = new JButton[argTypes.length];
                    Object[] args = new Object[argTypes.length];
                    for (int i = 0; i < argTypes.length; i++) {
                        args[i] = new Object();
                    }
                    for (int i = 0; i < argTypes.length; i++) {
                        constructorArgmentButtons[i] = new JButton("引数" + (i + 1));
                        int finalI = i;
                        constructorArgmentButtons[i].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                ChangeValueDialog dialog = new ChangeValueDialog(appFrame, argTypes[finalI], args, finalI);
                                dialog.setVisible(true);
                            }
                        });
                        executeButtonPanel.add(constructorArgmentButtons[i]);
                        System.out.println("button num " + i);
                        constructorArgmentButtons[i].setAlignmentX(0.5f);
                    }
                    executeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                for (int i = 0; i < args.length; i++) {
                                    System.out.println(args[i]);
                                }
                                generatedObject = targetConstructor.newInstance(args);
                            } catch (InstantiationException ex) {
                                ex.printStackTrace();
                            } catch (IllegalAccessException ex) {
                                ex.printStackTrace();
                            } catch (InvocationTargetException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });

                }
                executeButton.setAlignmentX(0.5f);
                executeButtonPanel.add(executeButton);
                this.add(executeButtonPanel);
                revalidate();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void setTargetMemberData(MemberData targetMemberData) {
        this.targetMemberData = targetMemberData;
    }
}
