import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.*;

public class ControlMethodPanel extends InterpretPanel {

    private AppFrame appFrame;
    private DisplayInsideArrayPanel displayInsideArrayPanel;
    private Dialog parentDialog;
    private ControlConstructorPanel controlConstructorPanel;
    private JPanel buttonPanel;
    private JPanel[] checkContainValueInArgPanel;
    private JButton[] methodArgumentButtons;
    private JButton executeButton;
    private JLabel[] checkContainValueLabel;
    private MemberData targetMethodData;

    private DataHolder dataHolder;

    public class DataHolder {
        public Object[] args;
    }

    public DataHolder getDataHolder() {
        return dataHolder;
    }

    public ControlMethodPanel(AppFrame appFrame, ControlConstructorPanel controlConstructorPanel, DisplayInsideArrayPanel displayInsideArrayPanel) {
        this.appFrame = appFrame;
        this.controlConstructorPanel = controlConstructorPanel;
        this.displayInsideArrayPanel = displayInsideArrayPanel;
    }

    public ControlMethodPanel(Dialog parentDialog, ControlConstructorPanel controlConstructorPanel) {
        this.parentDialog = parentDialog;
        this.controlConstructorPanel = controlConstructorPanel;
    }

    @Override
    void setPanelLayout() {
        setLayout(new BorderLayout());
    }

    @Override
    void setupComponent() {
        executeButton = new JButton("実行");
        executeButton.setAlignmentX(0.5f);
        initButtonPanel();
    }

    private void initButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setBackground(Color.PINK);
    }

    public void setTargetMethodData(MemberData targetMethodData) {
        //選択リスト項目が切り替わるたび呼ばれる
        this.targetMethodData = targetMethodData;
        dataHolder = new DataHolder();
    }

    public void updateCheckContainValueLabel(int index) {
        checkContainValueLabel[index].setText(dataHolder.args[index] == null ? "      " : "OK");
    }

    public void setMethodDataComponent() {
        this.remove(buttonPanel);
        initButtonPanel();
        if (!(targetMethodData.getMember() instanceof Method)) {
            return;
        }
        Method targetMethod = (Method) targetMethodData.getMember();
        Type[] argTypes = targetMethod.getGenericParameterTypes();
        dataHolder.args = new Object[argTypes.length];
        methodArgumentButtons = new JButton[argTypes.length];
        checkContainValueInArgPanel = new JPanel[argTypes.length];
        checkContainValueLabel = new JLabel[argTypes.length];
        for (int i = 0; i < argTypes.length; i++) {
            checkContainValueInArgPanel[i] = new JPanel();
            checkContainValueInArgPanel[i].setLayout(new BoxLayout(checkContainValueInArgPanel[i], BoxLayout.X_AXIS));
            checkContainValueInArgPanel[i].setOpaque(false);
            methodArgumentButtons[i] = new JButton("引数" + (i + 1));
            checkContainValueLabel[i] = new JLabel();
            checkContainValueLabel[i].setForeground(AppStyle.BLUE);
            updateCheckContainValueLabel(i);
            int finalI = i;
            methodArgumentButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChangeValueDialog dialog = new ChangeValueDialog(appFrame, ControlMethodPanel.this, argTypes[finalI], finalI);
                    dialog.setVisible(true);
                }
            });
            checkContainValueInArgPanel[i].add(methodArgumentButtons[i]);
            checkContainValueInArgPanel[i].add(checkContainValueLabel[i]);
            buttonPanel.add(checkContainValueInArgPanel[i]);
        }
        if (executeButton.getActionListeners().length >= 1) {
            executeButton.removeActionListener(executeButton.getActionListeners()[0]);
        }
        //メソッド実行ボタンの挙動設定
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Object returnObject = null;
                    targetMethod.setAccessible(true);
                    if (Modifier.isStatic(targetMethod.getModifiers())) {
                        returnObject = targetMethod.invoke(null, dataHolder.args);
                    } else {
                        if (parentDialog == null && appFrame.isArrayPanelVisible()) {
                            returnObject = targetMethod.invoke(displayInsideArrayPanel.getArrayElement(), dataHolder.args);
                        } else {
                            returnObject = targetMethod.invoke(controlConstructorPanel.getDataHolder().generatedObject, dataHolder.args);
                        }
                    }
                    ConsoleAreaPanel.appendNewLog("Succeed in executing method.(" + targetMethod.getName() + ")");
                    if ("void".equals(targetMethod.getReturnType().getTypeName())) {
                        ConsoleAreaPanel.appendNewLog("return value is Nothing");
                    } else {
                        ConsoleAreaPanel.appendNewLog("return value is " + returnObject);
                    }
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                    ConsoleAreaPanel.appendNewLog("Throw IllegalAccessException.");
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                    ConsoleAreaPanel.appendNewLog("Throw InvocationTargetException.");
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                    ConsoleAreaPanel.appendNewLog("Throw IllegalArgumentException.");
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                    ConsoleAreaPanel.appendNewLog("Throw NullPointerException.");
                } catch (ExceptionInInitializerError er) {
                    er.printStackTrace();
                    ConsoleAreaPanel.appendNewLog("Throw ExceptionInInitializerError.");
                }
            }
        });
        executeButton.setAlignmentX(0.5f);
        buttonPanel.add(executeButton);
        this.add(buttonPanel);
    }

    public void setExecuteButtonState(boolean enable) {
        executeButton.setEnabled(enable);
    }
}
