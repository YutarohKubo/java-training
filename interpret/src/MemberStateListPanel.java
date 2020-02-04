import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class MemberStateListPanel extends InterpretPanel implements ListCellRenderer<MemberData> {

    public MemberStateListPanel() {

    }

    @Override
    void setPanelLayout() {
        this.setLayout(new BorderLayout());
    }

    @Override
    void setupComponent() {

    }

    @Override
    public Component getListCellRendererComponent(JList<? extends MemberData> list, MemberData value, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));
        itemPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        itemPanel.setOpaque(false);
        JLabel memberLabel = new JLabel(value.toString());
        JLabel memberValueLabel = new JLabel();
        itemPanel.add(memberLabel);
        itemPanel.setBorder(new CompoundBorder(itemPanel.getBorder(), new EmptyBorder(0, 0, 0, 0)));
        if (isSelected) {
            //項目選択時の色反映させるための処理
            itemPanel.setOpaque(true);
            itemPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
            itemPanel.setBackground(Color.YELLOW);
        }
        return null;
    }
}
