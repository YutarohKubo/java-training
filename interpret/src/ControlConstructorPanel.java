import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        executeButton.setAlignmentX(0.5f);
        initButtonPanel();
    }

    private void initButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setBackground(AppStyle.CANARIA);
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
        if (executeButton.getActionListeners().length >= 2) {
            executeButton.removeActionListener(executeButton.getActionListeners()[0]);
        }
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dataHolder.generatedObject = targetConstructor.newInstance(dataHolder.args);
                    ConsoleAreaPanel.appendNewLog("Succeed in Creating Object.(" + targetConstructor.getName() + ")");
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                }
            }
        });
        System.out.println("action listener length before = " + executeButton.getActionListeners().length);
        executeButton.setAlignmentX(0.5f);
        buttonPanel.add(executeButton);
        this.add(buttonPanel);
    }

}
