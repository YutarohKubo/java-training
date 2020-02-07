import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayInsideArrayPanel extends InterpretPanel implements ListCellRenderer<ListItemData> {

    private JList<ListItemData> jListArray;
    private DefaultListModel<ListItemData> model;
    private List<ListItemData> listArray = new ArrayList<>();
    private boolean fragChangeItem = false;

    public DisplayInsideArrayPanel() {

    }

    @Override
    void setPanelLayout() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.CYAN);
    }

    @Override
    void setupComponent() {
        model = new DefaultListModel<>();
        jListArray = new JList<>(model);
        jListArray.setBackground(Color.CYAN);
        jListArray.setCellRenderer(this);
        jListArray.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                fragChangeItem = true;
            }
        });
        JScrollPane sp = new JScrollPane();
        sp.getViewport().setView(jListArray);
        sp.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        this.add(new JTextField("hehehe"), BorderLayout.NORTH);
        this.add(sp);
    }

    public void makeList(int length) {
        for (int i = 0; i < length ; i++) {
            listArray.add(new ListItemData(i, null));
            model.addElement(new ListItemData(i, null));
        }
    }

    public void clearList() {
        if (listArray != null) {
            listArray.clear();
        }
        if (model != null) {
            model.clear();
        }
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends ListItemData> list, ListItemData value, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.PAGE_AXIS));
        itemPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        itemPanel.setOpaque(false);
        JLabel numberLabel = new JLabel(Integer.toString(value.getItemNumber()));
        JLabel memberNameLabel = new JLabel(value.toString());
        itemPanel.add(numberLabel);
        itemPanel.add(memberNameLabel);
        if (isSelected) {
            //項目選択時の色反映させるための処理
            itemPanel.setOpaque(true);
            itemPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
            itemPanel.setBackground(Color.RED);
            if (fragChangeItem) {
                fragChangeItem = false;
            }
        }
        return itemPanel;
    }
}
