import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class ControlConstructorPanel extends InterpretPanel {

    private AppFrame appFrame;
    private Dialog parentDialog;
    private JPanel buttonPanel;
    private JPanel[] checkContainValueInArgPanel;
    private JButton[] constructorArgumentButtons;
    private JButton executeButton;
    private JLabel[] checkContainValueLabel;
    private MemberData targetConstructorData;

    private DataHolder dataHolder;

    public class DataHolder {
        public Object generatedObject;
        public Object[] args;
    }

    public DataHolder getDataHolder() {
        return dataHolder;
    }

    public ControlConstructorPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    public ControlConstructorPanel(Dialog parentDialog) {
        this.parentDialog = parentDialog;
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
        buttonPanel.setBackground(Color.ORANGE);
    }

    public void setTargetConstructorData(MemberData targetConstructorData) {
        this.targetConstructorData = targetConstructorData;
        if (dataHolder == null) {
            dataHolder = new DataHolder();
        }
    }

    public void updateCheckContainValueLabel(int index) {
        checkContainValueLabel[index].setText(dataHolder.args[index] == null ? "      " : "OK");
    }

    public void setConstructorDataComponent() {
        this.remove(buttonPanel);
        initButtonPanel();
        if (!(targetConstructorData.getMember() instanceof Constructor)) {
            return;
        }
        Constructor targetConstructor = (Constructor) targetConstructorData.getMember();
        if (!Modifier.isPublic(targetConstructor.getModifiers())) {
            targetConstructor.setAccessible(true);
        }
        Type[] argTypes = targetConstructor.getGenericParameterTypes();
        dataHolder.args = new Object[argTypes.length];
        constructorArgumentButtons = new JButton[argTypes.length];
        checkContainValueInArgPanel = new JPanel[argTypes.length];
        checkContainValueLabel = new JLabel[argTypes.length];
        for (int i = 0; i < argTypes.length; i++) {
            checkContainValueInArgPanel[i] = new JPanel();
            checkContainValueInArgPanel[i].setLayout(new BoxLayout(checkContainValueInArgPanel[i], BoxLayout.X_AXIS));
            checkContainValueInArgPanel[i].setOpaque(false);
            constructorArgumentButtons[i] = new JButton("引数" + (i + 1));
            checkContainValueLabel[i] = new JLabel();
            checkContainValueLabel[i].setForeground(AppStyle.BLUE);
            updateCheckContainValueLabel(i);
            int finalI = i;
            constructorArgumentButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChangeValueDialog dialog = new ChangeValueDialog(appFrame, ControlConstructorPanel.this, argTypes[finalI], finalI);
                    dialog.setVisible(true);
                }
            });
            checkContainValueInArgPanel[i].add(constructorArgumentButtons[i]);
            checkContainValueInArgPanel[i].add(checkContainValueLabel[i]);
            buttonPanel.add(checkContainValueInArgPanel[i]);
        }
        System.out.println("action listener length before = " + executeButton.getActionListeners().length);
        if (executeButton.getActionListeners().length >= 1) {
            executeButton.removeActionListener(executeButton.getActionListeners()[0]);
        }
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataHolder.generatedObject = targetConstructor.newInstance(dataHolder.args);
                    ConsoleAreaPanel.appendNewLog("Succeed in Creating Object.(" + targetConstructor.getName() + ")");
                } catch (InstantiationException ex) {
                    //抽象クラスなど、インスタンス化しようとしたとき
                    ConsoleAreaPanel.appendNewLog("Throw InstantiationException.");
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    //アクセスできないコンストラクタを呼び出そうとしたとき
                    ConsoleAreaPanel.appendNewLog("Throw IllegalAccessException.");
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    //コンストラクタ自身が例外をThrowしたとき
                    ConsoleAreaPanel.appendNewLog("Throw InvocationTargetException.");
                    ex.printStackTrace();
                } catch (IllegalArgumentException ex) {
                    //targetConstructorの引数と一致しないとき
                    ConsoleAreaPanel.appendNewLog("Throw IllegalArgumentException.");
                    ex.printStackTrace();
                }
            }
        });
        System.out.println("action listener length before = " + executeButton.getActionListeners().length);
        executeButton.setAlignmentX(0.5f);
        buttonPanel.add(executeButton);
        this.add(buttonPanel);
    }

    public void setExecuteButtonState(boolean enable) {
        executeButton.setEnabled(enable);
    }

}
