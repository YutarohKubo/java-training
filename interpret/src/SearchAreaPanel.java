import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchAreaPanel extends InterpretPanel {

    private DeclaredMemberListPanel declaredMemberListPanel;

    private JTextField textSearchField;
    private JButton buttonSearch;

    public SearchAreaPanel(DeclaredMemberListPanel declaredMemberListPanel) {
        this.setBackground(AppStyle.INDIGO);
        this.declaredMemberListPanel = declaredMemberListPanel;
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
                declaredMemberListPanel.setupJListMember(textSearchField.getText());
            }
        });
        this.add(textSearchLabel);
        this.add(textSearchField);
        this.add(buttonSearch);
    }

}
