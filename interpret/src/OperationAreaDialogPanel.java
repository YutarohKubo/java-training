import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationAreaDialogPanel extends InterpretPanel{

    JPanel panelButtonArea;
    ControlMemberPanel controlMemberPanel;
    ChangeValueDialog changeValueDialog;
    JButton buttonDetermine;
    JButton buttonClose;
    //呼び出し元Windowのメンバ種類パネル
    JPanel panel;
    int index;

    public OperationAreaDialogPanel(ChangeValueDialog changeValueDialog, ControlMemberPanel controlMemberPanel) {
        this.setBackground(AppStyle.CHERRY_BLOSSOMS);
        this.controlMemberPanel = controlMemberPanel;
        this.changeValueDialog = changeValueDialog;
        this.panel = changeValueDialog.panel;
        this.index = changeValueDialog.changeIndex;
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
        buttonClose = new JButton("閉じる");
        buttonDetermine = new JButton("決定");
        buttonDetermine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panel instanceof ControlConstructorPanel) {
                    ((ControlConstructorPanel)panel).getDataHolder().args[index] = controlMemberPanel.getConstructorPanelDataHolder().generatedObject;
                    ((ControlConstructorPanel)panel).updateCheckContainValueLabel(index);
                } else if (panel instanceof ControlMethodPanel) {
                    ((ControlMethodPanel)panel).getDataHolder().args[index] = controlMemberPanel.getConstructorPanelDataHolder().generatedObject;
                    ((ControlMethodPanel)panel).updateCheckContainValueLabel(index);
                } else if (panel instanceof ControlFieldPanel) {
                    ((ControlFieldPanel)panel).getDataHolder().value = controlMemberPanel.getConstructorPanelDataHolder().generatedObject;
                    ((ControlFieldPanel)panel).updateCheckContainValueLabel();
                }
                changeValueDialog.setVisible(false);
            }
        });
        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeValueDialog.setVisible(false);
            }
        });
    }

    public void setButtonDetermineState(boolean enabled) {
        buttonDetermine.setEnabled(enabled);
    }

    private void addComponent() {
        this.add(controlMemberPanel);
        panelButtonArea.add(setMargin(buttonClose, 0, 0, 0, 0));
        panelButtonArea.add(setMargin(buttonDetermine, 0, 0, 0, 0));
        this.add(panelButtonArea);
    }
}
