import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class DeclaredMemberListPanel extends InterpretPanel {

    private JList<MemberData> jListMember;
    private DefaultListModel<MemberData> model;
    private List<MemberData> listMember = new ArrayList<>();

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
        JScrollPane sp = new JScrollPane();
        sp.getViewport().setView(jListMember);
        sp.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        this.add(sp);
    }

    public void setupJListMember (String packageAndClassName) {
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

}
