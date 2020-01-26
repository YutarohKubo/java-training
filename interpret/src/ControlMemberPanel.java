import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class ControlMemberPanel extends InterpretPanel {

    private PanelInputText inputToMemberPanel;
    private JPanel executeButtonPanel;
    private JButton executeChangeValueButton;
    private JButton executeMethodButton;
    private JButton generateConstructorButton;

    private MemberData targetMemberData;
    private Object generatedObject;

    public enum Mode {
        FIELD,
        METHOD,
        CONSTRUCTOR,
    }

    public ControlMemberPanel() {
        addComponent();
    }

    @Override
    void setPanelLayout() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    @Override
    void setupComponent() {
        inputToMemberPanel = new PanelInputText("");
        executeButtonPanel = new JPanel();
        executeButtonPanel.setLayout(new BoxLayout(executeButtonPanel, BoxLayout.PAGE_AXIS));
        executeChangeValueButton = new JButton("実行");
        executeChangeValueButton.setAlignmentX(0.5f);
        executeChangeValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        executeMethodButton = new JButton("実行");
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
        });
        generateConstructorButton = new JButton("生成");
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
        });
    }

    private void addComponent() {
        this.add(inputToMemberPanel);
        executeButtonPanel.add(executeChangeValueButton);
        executeButtonPanel.add(executeMethodButton);
        executeButtonPanel.add(generateConstructorButton);
        this.add(executeButtonPanel);
        setComponentMode(Mode.FIELD);
    }

    public void setComponentMode(Mode mode) {
        switch (mode) {
            case FIELD:
                inputToMemberPanel.setVisible(true);
                inputToMemberPanel.setTitle("値");
                executeChangeValueButton.setVisible(true);
                executeMethodButton.setVisible(false);
                generateConstructorButton.setVisible(false);
                break;
            case METHOD:
                inputToMemberPanel.setVisible(true);
                inputToMemberPanel.setTitle("引数");
                executeChangeValueButton.setVisible(false);
                executeMethodButton.setVisible(true);
                generateConstructorButton.setVisible(false);
                break;
            case CONSTRUCTOR:
                inputToMemberPanel.setVisible(true);
                inputToMemberPanel.setTitle("引数");
                executeChangeValueButton.setVisible(false);
                executeMethodButton.setVisible(false);
                generateConstructorButton.setVisible(true);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void setTargetMemberData(MemberData targetMemberData) {
        this.targetMemberData = targetMemberData;
    }
}
