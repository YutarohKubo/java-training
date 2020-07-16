package dc2_4;

import javax.swing.*;
import java.awt.*;

public class ColorBoxRenderer implements ListCellRenderer<Property.AppColor> {

    private static final Color mouseFocusedColor = new Color(176, 196, 222);

    @Override
    public Component getListCellRendererComponent(JList<? extends Property.AppColor> list, Property.AppColor value, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new FlowLayout());
        itemPanel.setOpaque(false);
        JLabel colorNameLabel = new JLabel(value.getName());
        ColorChipPanel colorChipPanel = new ColorChipPanel(value.getColor());
        itemPanel.add(colorNameLabel);
        itemPanel.add(colorChipPanel);
        if (isSelected) {
            //項目選択時の色反映させるための処理
            itemPanel.setOpaque(true);
            itemPanel.setBackground(mouseFocusedColor);
        }
        return itemPanel;
    }

}
