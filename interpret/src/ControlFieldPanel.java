import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlFieldPanel extends InterpretPanel {

    private JButton changeValueButton;
    private JButton executeButton;
    private MemberData targetFieldData;

    private DataHolder dataHolder;

    public static class DataHolder {
        Object value;
    }

    public ControlFieldPanel () {
        addComponent();
    }

    @Override
    void setPanelLayout() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    @Override
    void setupComponent() {
        changeValueButton = new JButton("値変更");
        changeValueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        changeValueButton.setAlignmentX(0.5f);
        executeButton = new JButton("実行");
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        executeButton.setAlignmentX(0.5f);
    }

    private void addComponent() {
        this.add(changeValueButton);
        this.add(executeButton);
    }

    public void setTargetFieldData(MemberData targetFieldData) {
        this.targetFieldData = targetFieldData;
        this.dataHolder = new DataHolder();
    }
}
