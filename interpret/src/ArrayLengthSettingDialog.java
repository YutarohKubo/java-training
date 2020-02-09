import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ArrayLengthSettingDialog extends JDialog {

    public static final int DIALOG_WIDTH = 960;
    public static final int DIALOG_HEIGHT = 540;

    private PanelInputNumberSpinner numberSpinner;
    private JButton buttonOk;
    private JButton buttonBack;
    private MainAreaPanel mainAreaPanel;
    private OperationAreaPanel operationAreaPanel;

    public ArrayLengthSettingDialog(Frame frame, MainAreaPanel mainAreaPanel, OperationAreaPanel operationAreaPanel, Class<?> arrayTypeClazz) {
        super(frame, "配列の大きさ設定", true);
        this.mainAreaPanel = mainAreaPanel;
        this.operationAreaPanel = operationAreaPanel;
        setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        setLayout(new BorderLayout());
        JPanel lengthSettingPanel = new JPanel();
        lengthSettingPanel.setLayout(new BoxLayout(lengthSettingPanel, BoxLayout.PAGE_AXIS));
        numberSpinner = new PanelInputNumberSpinner("Array Length : ", 0, 0, Integer.MAX_VALUE, 1);
        buttonOk = new JButton("生成");
        buttonBack = new JButton("戻る");
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAreaPanel.makeArray(arrayTypeClazz, numberSpinner.getValue());
                mainAreaPanel.setupMemberList(arrayTypeClazz);
                mainAreaPanel.showArrayPanel();
                operationAreaPanel.changeButtonCreateArrayModeToBack();
                setVisible(false);
            }
        });
        buttonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        JPanel buttonGroupingPanel = new JPanel();
        buttonGroupingPanel.setLayout(new FlowLayout());
        buttonGroupingPanel.add(buttonBack);
        buttonGroupingPanel.add(buttonOk);
        lengthSettingPanel.add(numberSpinner);
        lengthSettingPanel.add(buttonGroupingPanel);
        this.add(lengthSettingPanel);
    }

}
