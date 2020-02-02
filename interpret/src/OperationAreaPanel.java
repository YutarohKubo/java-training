import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class OperationAreaPanel extends InterpretPanel {

    JPanel panelButtonArea;
    ControlMemberPanel controlMemberPanel;
    JButton buttonModifyVariable;
    JButton buttonExecuteMethod;
    JButton buttonCreateObject;
    JButton buttonCreateArrayMode;
    JButton buttonDisplayDeclaredMember;
    JButton buttonReset;

    public OperationAreaPanel (ControlMemberPanel controlMemberPanel) {
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
        buttonReset = new JButton("リセット");
        buttonReset.setAlignmentX(0.5f);
    }

    private void addComponent() {
        this.add(controlMemberPanel);
        panelButtonArea.add(setMargin(buttonCreateArrayMode, 0, 0, 0, 0));
        panelButtonArea.add(setMargin(buttonDisplayDeclaredMember, 0, 0, 0, 0));
        panelButtonArea.add(setMargin(buttonReset, 0, 0, 0, 0));
        this.add(panelButtonArea);
    }
}
