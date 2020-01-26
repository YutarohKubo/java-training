import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class DeclaredMemberListPanel extends InterpretPanel implements ListCellRenderer<MemberData> {

    private ControlMemberPanel controlMemberPanel;
    private JList<MemberData> jListMember;
    private DefaultListModel<MemberData> model;
    private List<MemberData> listMember = new ArrayList<>();

    public DeclaredMemberListPanel(ControlMemberPanel controlMemberPanel) {
        this.controlMemberPanel = controlMemberPanel;
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

    public void setupJListMember(String packageAndClassName) {
        try {
            Class<?> targetClazz = Class.forName(packageAndClassName);
            while (targetClazz != null) {
                setListMemberToMember(targetClazz.getDeclaredConstructors(), MemberData.Type.CONSTRUCTOR);
                setListMemberToMember(targetClazz.getDeclaredFields(), MemberData.Type.FIELD);
                setListMemberToMember(targetClazz.getDeclaredMethods(), MemberData.Type.METHOD);
                targetClazz = (Class<?>) targetClazz.getGenericSuperclass();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setListMemberToMember(Member[] members, MemberData.Type type) {
        for (Member m : members) {
            MemberData memberData = new MemberData(type, m);
            listMember.add(memberData);
            model.addElement(memberData);
        }
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends MemberData> list, MemberData value, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.PAGE_AXIS));
        itemPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        itemPanel.setOpaque(false);
        JLabel memberLabel = new JLabel(value.toString());
        itemPanel.add(memberLabel);

        itemPanel.setBorder(new CompoundBorder(itemPanel.getBorder(), new EmptyBorder(0, 0, 0, 0)));

        if (isSelected) {
            //項目選択時の色反映させるための処理
            itemPanel.setOpaque(true);
            itemPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
            itemPanel.setBackground(Color.YELLOW);
            controlMemberPanel.setTargetMemberData(value);
            switch (value.getMemberType()) {
                case FIELD:
                    controlMemberPanel.setComponentMode(ControlMemberPanel.Mode.FIELD);
                    break;
                case METHOD:
                    controlMemberPanel.setComponentMode(ControlMemberPanel.Mode.METHOD);
                    break;
                case CONSTRUCTOR:
                    controlMemberPanel.setComponentMode(ControlMemberPanel.Mode.CONSTRUCTOR);
                    break;
                default:
            }
        }

        return itemPanel;
    }
}
