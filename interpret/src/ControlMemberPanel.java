import javax.swing.*;
import java.awt.*;
import java.lang.reflect.*;

public class ControlMemberPanel extends InterpretPanel {

    public static final String TAG_FIELD_SHOW = "field_panel";
    public static final String TAG_METHOD_SHOW = "method_panel";
    public static final String TAG_CONSTRUCTOR_SHOW = "constructor_panel";

    private CardLayout layout;
    private AppFrame appFrame;
    private ControlFieldPanel fieldPanel;
    private ControlMethodPanel methodPanel;
    private ControlConstructorPanel constructorPanel;
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

    public ControlMemberPanel(AppFrame appFrame, ControlFieldPanel fieldPanel, ControlMethodPanel methodPanel, ControlConstructorPanel constructorPanel) {
        this.appFrame = appFrame;
        this.fieldPanel = fieldPanel;
        this.methodPanel = methodPanel;
        this.constructorPanel = constructorPanel;
        addComponent();
    }

    @Override
    void setPanelLayout() {
        this.layout = new CardLayout();
        setLayout(layout);
    }

    @Override
    void setupComponent() {
        /*inputToMemberPanel = new PanelInputText("");
        initExecuteButtonPanel();
        executeChangeValueButton = new JButton("実行");
        executeChangeValueButton.setAlignmentX(0.5f);
        executeChangeValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });*/
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
        this.add(fieldPanel, TAG_FIELD_SHOW);
        this.add(methodPanel, TAG_METHOD_SHOW);
        this.add(constructorPanel, TAG_CONSTRUCTOR_SHOW);
        setComponentMode(MemberType.FIELD);
    }

    public void setComponentMode(MemberType type) {
        switch (type) {
            case FIELD:
                layout.show(this, TAG_FIELD_SHOW);
                /*executeChangeValueButton = new JButton("変更");
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
                revalidate();*/
                break;
            case METHOD:
                layout.show(this, TAG_METHOD_SHOW);
                methodPanel.setMethodDataComponent();
                /*if (targetMemberData.getMember() instanceof Method) {
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
                revalidate();*/
                break;
            case CONSTRUCTOR:
                layout.show(this, TAG_CONSTRUCTOR_SHOW);
                constructorPanel.setConstructorDataComponent();
                /*if (targetMemberData.getMember() instanceof Constructor) {
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
                break;*/
            default:
                //throw new IllegalArgumentException();
        }
    }

    public void setTargetMemberData(MemberData targetMemberData, MemberType type) {
        switch (type) {
            case FIELD:
                fieldPanel.setTargetFieldData(targetMemberData);
                break;
            case METHOD:
                methodPanel.setTargetMethodData(targetMemberData);
                break;
            case CONSTRUCTOR:
                constructorPanel.setTargetConstructorData(targetMemberData);
                break;
            default:
        }
    }
}
