import javax.swing.*;
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
        return null;
    }
}
