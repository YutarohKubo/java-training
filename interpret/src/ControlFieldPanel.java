import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class ControlFieldPanel extends InterpretPanel {

    private AppFrame appFrame;
    private DisplayInsideArrayPanel displayInsideArrayPanel;
    private Dialog parentDialog;
    private ControlConstructorPanel controlConstructorPanel;
    private JPanel buttonPanel;
    private JPanel checkContainValueInArgPanel;
    private JButton changeValueButton;
    private JButton executeButton;
    private JLabel checkContainValueLabel;
    /**
     * 操作対象フィールド
     */
    private MemberData targetFieldData;

    private DataHolder dataHolder;

    public class DataHolder {
        /**
         * 変更する値
         */
        public Object value;
    }

    public DataHolder getDataHolder() {
        return dataHolder;
    }

    public ControlFieldPanel(AppFrame appFrame, ControlConstructorPanel controlConstructorPanel, DisplayInsideArrayPanel displayInsideArrayPanel) {
        this.appFrame = appFrame;
        this.controlConstructorPanel = controlConstructorPanel;
        this.displayInsideArrayPanel = displayInsideArrayPanel;
    }

    public ControlFieldPanel(AppFrame appFrame, Dialog parentDialog, ControlConstructorPanel controlConstructorPanel) {
        this.appFrame = appFrame;
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
        buttonPanel.setBackground(Color.MAGENTA);
    }

    private void addComponent() {
        this.add(changeValueButton);
        this.add(executeButton);
    }

    public void setTargetFieldData(MemberData targetFieldData) {
        this.targetFieldData = targetFieldData;
        this.dataHolder = new DataHolder();
    }

    public void updateCheckContainValueLabel() {
        checkContainValueLabel.setText(dataHolder.value == null ? "      " : "OK");
    }

    public void setFieldDataComponent() {
        this.remove(buttonPanel);
        initButtonPanel();
        if (!(targetFieldData.getMember() instanceof Field)) {
            return;
        }
        Field targetField = (Field) targetFieldData.getMember();
        Type argType = targetField.getType();
        checkContainValueInArgPanel = new JPanel();
        checkContainValueInArgPanel.setLayout(new BoxLayout(checkContainValueInArgPanel, BoxLayout.X_AXIS));
        checkContainValueInArgPanel.setOpaque(false);
        changeValueButton = new JButton("変更");
        checkContainValueLabel = new JLabel();
        checkContainValueLabel.setForeground(AppStyle.BLUE);
        updateCheckContainValueLabel();
        if (changeValueButton.getActionListeners().length == 0) {
            changeValueButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChangeValueDialog dialog = new ChangeValueDialog(appFrame, ControlFieldPanel.this, argType, 0);
                    dialog.setVisible(true);
                }
            });
        }
        checkContainValueInArgPanel.add(changeValueButton);
        checkContainValueInArgPanel.add(checkContainValueLabel);
        if (executeButton.getActionListeners().length >= 1) {
            executeButton.removeActionListener(executeButton.getActionListeners()[0]);
        }
        //フィールド値変更ボタン
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
                try {
                    if (Modifier.isFinal(targetField.getModifiers())) {
                        modifiersField.setAccessible(true);
                        modifiersField.setInt(targetField,
                                targetField.getModifiers() & ~Modifier.FINAL);
                    }
                    if (!Modifier.isPublic(targetField.getModifiers())) {
                        targetField.setAccessible(true);
                    }
                    if (Modifier.isStatic(targetField.getModifiers())) {
                        targetField.set(null, dataHolder.value);
                        ConsoleAreaPanel.appendNewLog("Succeed in renewing field.(" + targetField.getName() + ")");
                    } else {
                        if (parentDialog == null && appFrame.isArrayPanelVisible()) {
                            targetField.set(displayInsideArrayPanel.getArrayElement(), dataHolder.value);
                            ConsoleAreaPanel.appendNewLog("Succeed in renewing field.(" + targetField.getName() + ")");
                        } else {
                            targetField.set(controlConstructorPanel.getDataHolder().generatedObject, dataHolder.value);
                            ConsoleAreaPanel.appendNewLog("Succeed in renewing field.(" + targetField.getName() + ")");
                        }
                    }
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                    ConsoleAreaPanel.appendNewLog("Throw IllegalAccessException.");
                }
            }
        });
        buttonPanel.add(checkContainValueInArgPanel);
        buttonPanel.add(executeButton);
        this.add(buttonPanel);
    }

    public void setExecuteButtonState(boolean enable) {
        executeButton.setEnabled(enable);
    }
}
