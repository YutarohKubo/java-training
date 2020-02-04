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

    public OperationAreaPanel (Frame frame, ControlMemberPanel controlMemberPanel) {
        this.frame = frame;
        this.controlMemberPanel = controlMemberPanel;
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
        buttonCreateArrayMode = new JButton("配列生成");
        buttonCreateArrayMode.setAlignmentX(0.5f);
        buttonDisplayDeclaredMember = new JButton("宣言一覧表示");
        buttonDisplayDeclaredMember.setAlignmentX(0.5f);
        buttonProperty = new JButton("プロパティ");
        buttonProperty.setAlignmentX(0.5f);
    }

    private void addComponent() {
        this.add(controlMemberPanel);
        buttonProperty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PropertyDialog propertyDialog = new PropertyDialog(frame, controlMemberPanel.getConstructorPanel(), controlMemberPanel.getMethodPanel(), controlMemberPanel.getFieldPanel());
                propertyDialog.setVisible(true);
            }
        });
        panelButtonArea.add(setMargin(buttonProperty, 0, 0, 0, 0));
        panelButtonArea.add(setMargin(buttonCreateArrayMode, 0, 0, 0, 0));
        panelButtonArea.add(setMargin(buttonDisplayDeclaredMember, 0, 0, 0, 0));
        this.add(panelButtonArea);
    }
}
