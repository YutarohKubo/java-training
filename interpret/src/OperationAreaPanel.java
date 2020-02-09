import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationAreaPanel extends InterpretPanel {

    AppFrame frame;
    private SearchAreaPanel searchAreaPanel;
    JPanel panelButtonArea;
    ControlMemberPanel controlMemberPanel;
    JButton buttonModifyVariable;
    JButton buttonExecuteMethod;
    JButton buttonCreateObject;
    JButton buttonCreateArrayMode;
    JButton buttonDisplayDeclaredMember;
    JButton buttonProperty;
    DisplayInsideArrayPanel displayInsideArrayPanel;
    MainAreaPanel mainAreaPanel;

    public OperationAreaPanel (AppFrame frame, ControlMemberPanel controlMemberPanel, MainAreaPanel mainAreaPanel, DisplayInsideArrayPanel displayInsideArrayPanel) {
        this.frame = frame;
        this.controlMemberPanel = controlMemberPanel;
        this.mainAreaPanel = mainAreaPanel;
        this.displayInsideArrayPanel = displayInsideArrayPanel;
        addComponent();
    }

    public void setSearchAreaPanel(SearchAreaPanel searchAreaPanel) {
        this.searchAreaPanel = searchAreaPanel;
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
                StatusDisplayDialog statusDisplayDialog = new StatusDisplayDialog(frame, displayInsideArrayPanel, controlMemberPanel.getConstructorPanel(), controlMemberPanel.getMethodPanel(), controlMemberPanel.getFieldPanel());
                statusDisplayDialog.setVisible(true);
            }
        });
        buttonCreateArrayMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String targetArrayType = searchAreaPanel.getSearchFieldText();
                Class<?> targetClazz = null;
                try {
                    targetClazz = Class.forName(targetArrayType);
                } catch (ClassNotFoundException ex) {
                    ConsoleAreaPanel.appendNewLog("Throw ClassNotFoundException.");
                    ex.printStackTrace();
                }
                ArrayLengthSettingDialog arrayLengthSettingDialog = new ArrayLengthSettingDialog(frame, mainAreaPanel, OperationAreaPanel.this, targetClazz);
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
                String targetArrayType = searchAreaPanel.getSearchFieldText();
                Class<?> targetClazz = null;
                try {
                    targetClazz = Class.forName(targetArrayType);
                } catch (ClassNotFoundException ex) {
                    ConsoleAreaPanel.appendNewLog("Throw ClassNotFoundException.");
                    ex.printStackTrace();
                }
                ArrayLengthSettingDialog arrayLengthSettingDialog = new ArrayLengthSettingDialog(frame, mainAreaPanel, OperationAreaPanel.this, targetClazz);
                arrayLengthSettingDialog.setVisible(true);
            }
        });
    }
}
