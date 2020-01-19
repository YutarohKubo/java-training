import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class OperationAreaPanel extends InterpretPanel {

    JButton buttonModifyVariable;
    JButton buttonExecuteMethod;
    JButton buttonCreateObject;
    JButton buttonCreateArrayMode;
    JButton buttonDisplayDeclaredMember;
    JButton buttonReset;

    public OperationAreaPanel () {
        this.setBackground(AppStyle.CHERRY_BLOSSOMS);
    }

    @Override
    void setPanelLayout() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    @Override
    void setupComponent() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        buttonModifyVariable = new JButton("値変更");
        buttonModifyVariable.setAlignmentX(0.5f);
        buttonExecuteMethod = new JButton("メソッド実行");
        buttonExecuteMethod.setAlignmentX(0.5f);
        buttonCreateObject = new JButton("オブジェクト生成");
        buttonCreateObject.setAlignmentX(0.5f);
        buttonCreateArrayMode = new JButton("配列生成");
        buttonCreateArrayMode.setAlignmentX(0.5f);
        buttonDisplayDeclaredMember = new JButton("宣言一覧表示");
        buttonDisplayDeclaredMember.setAlignmentX(0.5f);
        buttonReset = new JButton("リセット");
        buttonReset.setAlignmentX(0.5f);
        this.add(setMargin(buttonModifyVariable, 30, 0, 30, 0));
        this.add(setMargin(buttonExecuteMethod, 0, 0, 30, 0));
        this.add(setMargin(buttonCreateObject, 0, 0, 30, 0));
        this.add(setMargin(buttonCreateArrayMode, 0, 0, 30, 0));
        this.add(setMargin(buttonDisplayDeclaredMember, 0, 0, 30, 0));
        this.add(setMargin(buttonReset, 0, 0, 30, 0));
    }

}
