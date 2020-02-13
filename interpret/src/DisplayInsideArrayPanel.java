import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 画面右側に出現する、配列の中身を表現するGUI断片
 */
public class DisplayInsideArrayPanel extends InterpretPanel implements ListCellRenderer<ListItemData> {

    private JList<ListItemData> jListArray;
    private JLabel jArrayTypeLabel;
    private DefaultListModel<ListItemData> model;
    private List<ListItemData> listArray = new ArrayList<>();
    private boolean fragChangeItem = false;
    /**
     * 生成オブジェクトを格納する配列
     */
    private Object arrayData;
    private int arrayLength;
    private int beforeSelectedItemIndex;

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
        jArrayTypeLabel = new JLabel();
        jArrayTypeLabel.setBackground(Color.WHITE);
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
        this.add(jArrayTypeLabel, BorderLayout.NORTH);
        this.add(sp, BorderLayout.CENTER);
    }

    public void makeList(Class<?> clazz, int length) {
        this.arrayLength = length;
        jListArray.setSelectedIndex(0);
        jArrayTypeLabel.setText("配列の型: " + clazz.getTypeName());
        arrayData = Array.newInstance(clazz, length);
        reloadListArray();
        jListArray.setSelectedIndex(0);
    }

    public void reMakeList(Class<?> clazz) {
        jArrayTypeLabel.setText("配列の型: " + clazz.getTypeName());
        arrayData = Array.newInstance(clazz, this.arrayLength);
        reloadListArray();
        jListArray.setSelectedIndex(0);
    }

    public void clearList() {
        if (listArray != null) {
            listArray.clear();
        }
        if (model != null) {
            model.clear();
        }
    }

    public void setArrayElement (Object createdInstance) {
        Array.set(arrayData, jListArray.getSelectedIndex(), createdInstance);
        beforeSelectedItemIndex = jListArray.getSelectedIndex();
        reloadListArray();
        jListArray.setSelectedIndex(beforeSelectedItemIndex);
    }

    public Object getArrayElement() {
        return Array.get(arrayData, jListArray.getSelectedIndex());
    }

    private void reloadListArray () {
        clearList();
        for (int i = 0; i < Array.getLength(arrayData) ; i++) {
            ListItemData itemData = new ListItemData(i, Array.get(arrayData, i));
            listArray.add(itemData);
            model.addElement(itemData);
        }
        revalidate();
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
