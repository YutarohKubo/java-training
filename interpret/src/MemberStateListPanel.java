import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class MemberStateListPanel extends InterpretPanel implements ListCellRenderer<MemberData> {

    private JList<MemberData> jListMember;
    private DefaultListModel<MemberData> model;
    private List<MemberData> listMember = new ArrayList<>();
    private ControlConstructorPanel controlConstructorPanel;

    public MemberStateListPanel(ControlConstructorPanel controlConstructorPanel) {
        this.controlConstructorPanel = controlConstructorPanel;
    }

    @Override
    void setPanelLayout() {
        this.setLayout(new BorderLayout());
        this.setBackground(AppStyle.CANARIA);
    }

    @Override
    void setupComponent() {
        model = new DefaultListModel<>();
        jListMember = new JList<>(model);
        jListMember.setBackground(AppStyle.SUNAIRO);
        jListMember.setCellRenderer(this);
        JScrollPane sp = new JScrollPane();
        sp.getViewport().setView(jListMember);
        sp.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        this.add(sp);
    }

    public void setupFieldInNowObject() {
        clearList();
        if (controlConstructorPanel.getDataHolder() == null || controlConstructorPanel.getDataHolder().generatedObject == null) {
            return;
        }
        Class<?> targetClazz = controlConstructorPanel.getDataHolder().generatedObject.getClass();
        while (targetClazz != null) {
            setListMemberToMember(targetClazz.getDeclaredFields(), MemberType.FIELD);
            targetClazz = (Class<?>) targetClazz.getGenericSuperclass();
        }

    }

    public void clearList() {
        if (listMember != null) {
            listMember.clear();
        }
        if (model != null) {
            model.clear();
        }
    }

    private void setListMemberToMember(Member[] members, MemberType type) {
        for (Member m : members) {
            MemberData memberData = new MemberData(type, m);
            listMember.add(memberData);
            model.addElement(memberData);
        }
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