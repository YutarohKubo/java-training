import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchAreaDialogPanel extends InterpretPanel {

    private DeclaredMemberListDialogPanel declaredMemberListDialogPanel;
    private JTextField textPackageClassField;
    private JTextField textMemberNameField;
    private JButton buttonShow;
    private JButton buttonPartialShow;

    public SearchAreaDialogPanel(DeclaredMemberListDialogPanel declaredMemberListDialogPanel) {
        this.setBackground(AppStyle.INDIGO);
        this.declaredMemberListDialogPanel = declaredMemberListDialogPanel;
    }

    @Override
    void setPanelLayout() {
        this.setLayout(new FlowLayout());
    }

    @Override
    void setupComponent () {
        JLabel textPackageClass = new JLabel("Package+Class");
        Font textSearchLabelFont = new Font("SansSerif", Font.PLAIN, 24);
        textPackageClass.setFont(textSearchLabelFont);
        textPackageClass.setForeground(AppStyle.WHITE);
        textPackageClassField = new JTextField(20);
        Font textPackageClassFieldFont = new Font("SansSerif", Font.PLAIN, 18);
        textPackageClassField.setFont(textPackageClassFieldFont);
        JLabel textMemberName = new JLabel("Member Name");
        textMemberName.setAlignmentX(1.0f);
        Font textMemberNameFont = new Font("SansSerif", Font.PLAIN, 16);
        textMemberName.setFont(textMemberNameFont);
        textMemberName.setForeground(AppStyle.WHITE);
        textMemberNameField = new JTextField(10);
        Font textMemberNameFieldFont = new Font("SansSerif", Font.PLAIN, 12);
        textMemberNameField.setFont(textMemberNameFieldFont);
        buttonShow = new JButton("表示");
        buttonShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Class<?> targetClazz = null;
                try {
                    targetClazz = Class.forName(textPackageClassField.getText());
                } catch (ClassNotFoundException ex) {
                    ConsoleAreaPanel.appendNewLog("Throw ClassNotFoundException.");
                    ex.printStackTrace();
                }
                declaredMemberListDialogPanel.setupJListMember(targetClazz, "");
            }
        });
        buttonPartialShow = new JButton("絞込み");
        buttonPartialShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Class<?> targetClazz = null;
                try {
                    targetClazz = Class.forName(textPackageClassField.getText());
                } catch (ClassNotFoundException ex) {
                    ConsoleAreaPanel.appendNewLog("Throw ClassNotFoundException.");
                    ex.printStackTrace();
                }
                declaredMemberListDialogPanel.setupJListMember(targetClazz, textMemberNameField.getText());
            }
        });
        this.add(textPackageClass);
        this.add(textPackageClassField);
        this.add(buttonShow);
        this.add(setMargin(textMemberName, 0, 10, 0, 0));
        this.add(textMemberNameField);
        this.add(buttonPartialShow);
    }

    public String getPackageClassFieldText() {
        return textPackageClassField.getText();
    }

    public void setPackageClassFieldText(String text) {
        textPackageClassField.setText(text);
    }

}
