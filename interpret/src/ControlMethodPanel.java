import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class ControlMethodPanel extends InterpretPanel {

    private AppFrame appFrame;
    private JPanel buttonPanel;
    private JButton[] methodArgumentButtons;
    private JButton executeButton;
    private MemberData targetMethodData;

    private DataHolder dataHolder;

    public static class DataHolder {
        public static Object[] args;
    }

    public ControlMethodPanel(AppFrame appFrame) {
        this.appFrame = appFrame;
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
        buttonPanel.setBackground(Color.RED);
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
        DataHolder.args = new Object[argTypes.length];
        methodArgumentButtons = new JButton[argTypes.length];
        for (int i = 0; i < argTypes.length; i++) {
            System.out.println(argTypes[i].toString());
            methodArgumentButtons[i] = new JButton("引数" + (i + 1));
            int finalI = i;
            methodArgumentButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChangeValueDialog dialog = new ChangeValueDialog(appFrame, ControlMethodPanel.this, argTypes[finalI], finalI);
                    dialog.setVisible(true);
                }
            });
            buttonPanel.add(methodArgumentButtons[i]);
            methodArgumentButtons[i].setAlignmentX(0.5f);
        }
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int i = 0; i < DataHolder.args.length; i++) {
                        System.out.println("targetMethod" + targetMethod.toString() + " " + i + " : " + DataHolder.args[i].toString() + " arg length = " + DataHolder.args.length);
                    }
                    targetMethod.setAccessible(true);
                    targetMethod.invoke(ControlConstructorPanel.DataHolder.generatedObject, DataHolder.args);
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
