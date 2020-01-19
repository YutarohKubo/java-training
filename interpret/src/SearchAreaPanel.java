import javax.swing.*;
import java.awt.*;

public class SearchAreaPanel extends InterpretPanel {

    JTextField textSearchField;
    JButton buttonSearch;

    public SearchAreaPanel() {
        this.setBackground(AppStyle.INDIGO);
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
        this.add(textSearchLabel);
        this.add(textSearchField);
    }

}
