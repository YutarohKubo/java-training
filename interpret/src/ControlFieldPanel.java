import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class ControlFieldPanel extends InterpretPanel {

    private AppFrame appFrame;
    private JPanel buttonPanel;
    private JButton changeValueButton;
    private JButton executeButton;
    private MemberData targetFieldData;

    private DataHolder dataHolder;

    public static class DataHolder {
        public static Object value;
    }

    public ControlFieldPanel (AppFrame appFrame) {
        this.appFrame = appFrame;
        //addComponent();
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

    private void initButtonPanel () {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setBackground(Color.BLUE);
    }

    private void addComponent() {
        this.add(changeValueButton);
        this.add(executeButton);
    }

    public void setTargetFieldData(MemberData targetFieldData) {
        this.targetFieldData = targetFieldData;
        this.dataHolder = new DataHolder();
    }

    public void setFieldDataComponent () {
        this.remove(buttonPanel);
        initButtonPanel();
        if (!(targetFieldData.getMember() instanceof Field)) {
            return;
        }
        Field targetField = (Field) targetFieldData.getMember();
        Type argType = targetField.getType();
        changeValueButton = new JButton("値変更");
        changeValueButton.setAlignmentX(0.5f);
        changeValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(argType.toString());
                ChangeValueDialog dialog = new ChangeValueDialog(appFrame, ControlFieldPanel.this, argType, 0);
                dialog.setVisible(true);
            }
        });
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(targetFieldData.getMember() instanceof Field)) {
                    return;
                }
                Field targetField = (Field) targetFieldData.getMember();
                Field modifiersField = null;
                try {
                    modifiersField = Field.class.getDeclaredField("modifiers");
                } catch (NoSuchFieldException ex) {
                    ex.printStackTrace();
                }
                modifiersField.setAccessible(true);
                try {
                    modifiersField.setInt(targetField,
                            targetField.getModifiers() & ~Modifier.PRIVATE & ~Modifier.FINAL);
                    targetField.set(ControlConstructorPanel.DataHolder.generatedObject, DataHolder.value);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonPanel.add(changeValueButton);
        buttonPanel.add(executeButton);
        this.add(buttonPanel);
    }
}
