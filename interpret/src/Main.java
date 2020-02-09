import java.awt.*;

public class Main {

    public static void main(String[] args) {
        callInterpretApp();
    }

    private static void callInterpretApp () {
        AppFrame frame = new AppFrame("Interpret");

        ConsoleAreaPanel consoleAreaPanel = new ConsoleAreaPanel();

        DisplayInsideArrayPanel displayInsideArrayPanel = new DisplayInsideArrayPanel();
        MainAreaPanel mainAreaPanel = new MainAreaPanel(frame, displayInsideArrayPanel);
        ControlConstructorPanel constructorPanel = new ControlConstructorPanel(frame, displayInsideArrayPanel);
        ControlFieldPanel fieldPanel = new ControlFieldPanel(frame, constructorPanel, displayInsideArrayPanel);
        ControlMethodPanel methodPanel = new ControlMethodPanel(frame, constructorPanel, displayInsideArrayPanel);
        //メンバ操作部分
        ControlMemberPanel controlMemberPanel = new ControlMemberPanel(fieldPanel, methodPanel, constructorPanel);
        //操作ボタン配置部分
        OperationAreaPanel operationAreaPanel = new OperationAreaPanel(frame, controlMemberPanel, mainAreaPanel, displayInsideArrayPanel);
        //MainArea&Console部分
        CenterPanel centerPanel = new CenterPanel();
        DeclaredMemberListPanel declaredMemberListPanel = new DeclaredMemberListPanel(frame, controlMemberPanel, displayInsideArrayPanel);
        mainAreaPanel.setDeclaredMemberListPanel(declaredMemberListPanel);
        mainAreaPanel.add(declaredMemberListPanel, BorderLayout.CENTER);
        centerPanel.add(mainAreaPanel, BorderLayout.CENTER);
        centerPanel.add(consoleAreaPanel, BorderLayout.SOUTH);
        //パッケージ+クラス名入力部分
        SearchAreaPanel searchAreaPanel = new SearchAreaPanel(frame, declaredMemberListPanel, displayInsideArrayPanel);
        operationAreaPanel.setSearchAreaPanel(searchAreaPanel);
        frame.add(searchAreaPanel, BorderLayout.NORTH);
        frame.add(operationAreaPanel, BorderLayout.WEST);
        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

}
