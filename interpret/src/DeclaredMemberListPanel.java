import javax.swing.*;
import java.awt.*;

public class DeclaredMemberListPanel extends InterpretPanel {

    JList<String> jListMember;
    DefaultListModel<String> model;

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
}
