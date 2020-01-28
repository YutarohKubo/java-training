import java.awt.*;

public class Main {

    public static void main(String[] args) {
        AppFrame frame = new AppFrame("Interpret");

        ControlFieldPanel fieldPanel = new ControlFieldPanel();
        ControlMethodPanel methodPanel = new ControlMethodPanel();
        ControlConstructorPanel constructorPanel = new ControlConstructorPanel();
        //メンバ操作部分
        ControlMemberPanel controlMemberPanel = new ControlMemberPanel(frame, fieldPanel, methodPanel, constructorPanel);
        //操作ボタン配置部分
        OperationAreaPanel operationAreaPanel = new OperationAreaPanel(controlMemberPanel);
        //MainArea&Console部分
        CenterPanel centerPanel = new CenterPanel();
        MainAreaPanel mainAreaPanel = new MainAreaPanel();
        ConsoleAreaPanel consoleAreaPanel = new ConsoleAreaPanel();
        DeclaredMemberListPanel declaredMemberListPanel = new DeclaredMemberListPanel(controlMemberPanel);
        mainAreaPanel.add(declaredMemberListPanel);
        centerPanel.add(mainAreaPanel, BorderLayout.CENTER);
        centerPanel.add(consoleAreaPanel, BorderLayout.SOUTH);
        //パッケージ+クラス名入力部分
        SearchAreaPanel searchAreaPanel = new SearchAreaPanel(declaredMemberListPanel);
        frame.add(searchAreaPanel, BorderLayout.NORTH);
        frame.add(operationAreaPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

}
