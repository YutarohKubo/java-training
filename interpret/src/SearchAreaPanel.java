import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchAreaPanel extends InterpretPanel {

    private DeclaredMemberListPanel declaredMemberListPanel;
    private DisplayInsideArrayPanel displayInsideArrayPanel;
    private JTextField textSearchField;
    private AppFrame frame;
    private JButton buttonSearch;

    public SearchAreaPanel(AppFrame frame, DeclaredMemberListPanel declaredMemberListPanel, DisplayInsideArrayPanel displayInsideArrayPanel) {
        this.setBackground(AppStyle.INDIGO);
        this.frame = frame;
        this.declaredMemberListPanel = declaredMemberListPanel;
        this.displayInsideArrayPanel = displayInsideArrayPanel;
    }

    @Override
    void setPanelLayout() {
        this.setLayout(new FlowLayout());
    }

    @Override
    void setupComponent () {
        JLabel textSearchLabel = new JLabel("検索");
        Font textSearchLabelFont = new Font("SansSerif", Font.PLAIN, 24);
        textSearchLabel.setFont(textSearchLabelFont);
        textSearchLabel.setForeground(AppStyle.WHITE);
        textSearchField = new JTextField(50);
        Font textSearchFieldFont = new Font("SansSerif", Font.PLAIN, 18);
        textSearchField.setFont(textSearchFieldFont);
        textSearchField.setSize(new Dimension(500, 10));
        buttonSearch = new JButton("検索");
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Class<?> targetClazz = null;
                try {
                    targetClazz = Class.forName(textSearchField.getText());
                } catch (ClassNotFoundException ex) {
                    ConsoleAreaPanel.appendNewLog("Throw ClassNotFoundException.");
                    ex.printStackTrace();
                }
                declaredMemberListPanel.setupJListMember(targetClazz);
                if (frame.isArrayPanelVisible()) {
                    displayInsideArrayPanel.reMakeList(targetClazz);
                }
            }
        });
        this.add(textSearchLabel);
        this.add(textSearchField);
        this.add(buttonSearch);
    }

    public String getSearchFieldText() {
        return textSearchField.getText();
    }

}
