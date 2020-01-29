import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public class ControlConstructorPanel extends InterpretPanel {

    private AppFrame appFrame;
    private JPanel buttonPanel;
    private JButton[] constructorArgumentButtons;
    private JButton executeButton;
    private MemberData targetConstructorData;

    private DataHolder dataHolder;

    public static class DataHolder {
        public static Object generatedObject;
        public static Object[] args;
    }

    public ControlConstructorPanel (AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    @Override
    void setPanelLayout() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
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
    }

    public void setTargetConstructorData(MemberData targetConstructorData) {
        this.targetConstructorData = targetConstructorData;
        dataHolder = new DataHolder();
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
        for (int i = 0; i < argTypes.length; i++) {
            constructorArgumentButtons[i] = new JButton("引数" + (i + 1));
            int finalI = i;
            constructorArgumentButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChangeValueDialog dialog = new ChangeValueDialog(appFrame, ControlConstructorPanel.this, argTypes[finalI], finalI);
                    dialog.setVisible(true);
                }
            });
            buttonPanel.add(constructorArgumentButtons[i]);
            constructorArgumentButtons[i].setAlignmentX(0.5f);
        }
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DataHolder.generatedObject = targetConstructor.newInstance(DataHolder.args);
                } catch (InstantiationException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                } catch (InvocationTargetException ex) {
                    ex.printStackTrace();
                }
            }
        });
        executeButton.setAlignmentX(0.5f);
        buttonPanel.add(executeButton);
        this.add(buttonPanel);
    }

}
