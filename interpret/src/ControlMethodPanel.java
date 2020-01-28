import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class ControlMethodPanel extends InterpretPanel {

    private JPanel buttonPanel;
    private JButton[] methodArgumentButtons;
    private JButton executeButton;
    private MemberData targetMethodData;

    private DataHolder dataHolder;

    public static class DataHolder {
        Object[] args;
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
    }

    public void setTargetMethodData(MemberData targetMethodData) {
        this.targetMethodData = targetMethodData;
        dataHolder = new DataHolder();
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
        for (int i = 0; i < argTypes.length; i++) {
            methodArgumentButtons[i] = new JButton("引数" + (i + 1));
            methodArgumentButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            buttonPanel.add(methodArgumentButtons[i]);
            methodArgumentButtons[i].setAlignmentX(0.5f);
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
