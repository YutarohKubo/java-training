import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationAreaPanel extends InterpretPanel {

    Frame frame;
    JPanel panelButtonArea;
    ControlMemberPanel controlMemberPanel;
    JButton buttonModifyVariable;
    JButton buttonExecuteMethod;
    JButton buttonCreateObject;
    JButton buttonCreateArrayMode;
    JButton buttonDisplayDeclaredMember;
    JButton buttonProperty;
    MainAreaPanel mainAreaPanel;

    public OperationAreaPanel (Frame frame, ControlMemberPanel controlMemberPanel, MainAreaPanel mainAreaPanel) {
        this.frame = frame;
        this.controlMemberPanel = controlMemberPanel;
        this.mainAreaPanel = mainAreaPanel;
        addComponent();
    }

    @Override
    void setPanelLayout() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    @Override
    void setupComponent() {
        panelButtonArea = new JPanel();
        panelButtonArea.setLayout(new BoxLayout(panelButtonArea, BoxLayout.PAGE_AXIS));
        buttonProperty = new JButton("状態表示");
        buttonProperty.setAlignmentX(0.5f);
        buttonCreateArrayMode = new JButton("配列生成");
        buttonCreateArrayMode.setAlignmentX(0.5f);
        buttonDisplayDeclaredMember = new JButton("宣言一覧表示");
        buttonDisplayDeclaredMember.setAlignmentX(0.5f);
    }

    private void addComponent() {
        this.add(controlMemberPanel);
        buttonProperty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatusDisplayDialog statusDisplayDialog = new StatusDisplayDialog(frame, controlMemberPanel.getConstructorPanel(), controlMemberPanel.getMethodPanel(), controlMemberPanel.getFieldPanel());
                statusDisplayDialog.setVisible(true);
            }
        });
        buttonCreateArrayMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayLengthSettingDialog arrayLengthSettingDialog = new ArrayLengthSettingDialog(frame, mainAreaPanel, OperationAreaPanel.this);
                arrayLengthSettingDialog.setVisible(true);
            }
        });
        panelButtonArea.add(setMargin(buttonProperty, 0, 0, 0, 0));
        panelButtonArea.add(setMargin(buttonCreateArrayMode, 0, 0, 0, 0));
        panelButtonArea.add(setMargin(buttonDisplayDeclaredMember, 0, 0, 0, 0));
        this.add(panelButtonArea);
    }

    public void changeButtonCreateArrayModeToBack() {
        buttonCreateArrayMode.setText("戻る");
        if (buttonCreateArrayMode.getActionListeners().length >= 1) {
            buttonCreateArrayMode.removeActionListener(buttonCreateArrayMode.getActionListeners()[0]);
        }
        buttonCreateArrayMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAreaPanel.removeArrayPanel();
                changeButtonCreateArrayModeToCreate();
            }
        });
    }

    public void changeButtonCreateArrayModeToCreate() {
        buttonCreateArrayMode.setText("配列生成");
        if (buttonCreateArrayMode.getActionListeners().length >= 1) {
            buttonCreateArrayMode.removeActionListener(buttonCreateArrayMode.getActionListeners()[0]);
        }
        buttonCreateArrayMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayLengthSettingDialog arrayLengthSettingDialog = new ArrayLengthSettingDialog(frame, mainAreaPanel, OperationAreaPanel.this);
                arrayLengthSettingDialog.setVisible(true);
            }
        });
    }
}
