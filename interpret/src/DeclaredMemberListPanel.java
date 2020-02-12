import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class DeclaredMemberListPanel extends InterpretPanel implements ListCellRenderer<MemberData> {

    private ControlMemberPanel controlMemberPanel;
    private AppFrame frame;
    private DisplayInsideArrayPanel displayInsideArrayPanel;
    private JList<MemberData> jListMember;
    private DefaultListModel<MemberData> model;
    private List<MemberData> listMember = new ArrayList<>();
    private boolean fragChangeItem = false;

    public DeclaredMemberListPanel(AppFrame frame, ControlMemberPanel controlMemberPanel, DisplayInsideArrayPanel displayInsideArrayPanel) {
        this.frame = frame;
        this.controlMemberPanel = controlMemberPanel;
        this.displayInsideArrayPanel = displayInsideArrayPanel;
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
        jListMember.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                fragChangeItem = true;
                //Todo 設定した値のリセット動作処理を書く
            }
        });
        JScrollPane sp = new JScrollPane();
        sp.getViewport().setView(jListMember);
        sp.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        this.add(sp);
    }

    public void setupJListMember(String packageAndClassName) {
        clearList();
        try {
            Class<?> targetClazz = Class.forName(packageAndClassName);
            while (targetClazz != null) {
                setListMemberToMember(targetClazz.getDeclaredConstructors(), MemberType.CONSTRUCTOR, "");
                setListMemberToMember(targetClazz.getDeclaredFields(), MemberType.FIELD, "");
                setListMemberToMember(targetClazz.getDeclaredMethods(), MemberType.METHOD, "");
                targetClazz = (Class<?>) targetClazz.getGenericSuperclass();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setupJListMember(Class<?> clazz, String memberName) {
        clearList();
        Class<?> targetClazz = clazz;
        while (targetClazz != null) {
            setListMemberToMember(targetClazz.getDeclaredConstructors(), MemberType.CONSTRUCTOR, memberName);
            setListMemberToMember(targetClazz.getDeclaredFields(), MemberType.FIELD, memberName);
            setListMemberToMember(targetClazz.getDeclaredMethods(), MemberType.METHOD, memberName);
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

    private void setListMemberToMember(Member[] members, MemberType type, String partialMatchStr) {
        for (Member m : members) {
            if (m != null && partialMatchStr != null && m.getName().toLowerCase().contains(partialMatchStr.toLowerCase())) {
                MemberData memberData = new MemberData(type, m);
                listMember.add(memberData);
                model.addElement(memberData);
            }
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
            if (Modifier.isStatic(value.getMember().getModifiers()) || value.getMemberType() == MemberType.CONSTRUCTOR) {
                controlMemberPanel.setExecuteButtonState(value.getMemberType(), true);
            } else {
                if (frame.isArrayPanelVisible()) {
                    //非staticなField,Methodに関しては、オブジェクトが生成されていなければ、実行ボタンを無効にする
                    if (displayInsideArrayPanel.getArrayElement() == null) {
                        controlMemberPanel.setExecuteButtonState(value.getMemberType(), false);
                    } else {
                        controlMemberPanel.setExecuteButtonState(value.getMemberType(), true);
                    }
                } else {
                    //非staticなField,Methodに関しては、オブジェクトが生成されていなければ、実行ボタンを無効にする
                    if (controlMemberPanel.getConstructorPanelDataHolder() == null || controlMemberPanel.getConstructorPanelDataHolder().generatedObject == null) {
                        controlMemberPanel.setExecuteButtonState(value.getMemberType(), false);
                    } else {
                        controlMemberPanel.setExecuteButtonState(value.getMemberType(), true);
                    }
                }
            }
            //項目選択時の色反映させるための処理
            itemPanel.setOpaque(true);
            itemPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
            itemPanel.setBackground(Color.YELLOW);
            if (fragChangeItem) {
                controlMemberPanel.setTargetMemberData(value, value.getMemberType());
                controlMemberPanel.setComponentMode(value.getMemberType());
                fragChangeItem = false;
            }
        }

        return itemPanel;
    }
}
