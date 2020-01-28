import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class ControlConstructorPanel extends InterpretPanel {

    private JPanel buttonPanel;
    private JButton[] constructorArgmentButtons;
    private JButton executeButton;
    private MemberData targetConstructorData;

    private DataHolder dataHolder;

    public static class DataHolder {
        Object[] args;
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
        constructorArgmentButtons = new JButton[argTypes.length];
        for (int i = 0; i < argTypes.length; i++) {
            constructorArgmentButtons[i] = new JButton("引数" + (i + 1));
            constructorArgmentButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            buttonPanel.add(constructorArgmentButtons[i]);
            constructorArgmentButtons[i].setAlignmentX(0.5f);
        }
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        executeButton.setAlignmentX(0.5f);
        buttonPanel.add(executeButton);
        this.add(buttonPanel);
    }
}
