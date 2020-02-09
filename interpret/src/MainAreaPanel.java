import java.awt.*;

public class MainAreaPanel extends InterpretPanel {

    private DisplayInsideArrayPanel displayInsideArrayPanel;
    private DeclaredMemberListPanel declaredMemberListPanel;
    private AppFrame frame;

    public MainAreaPanel (AppFrame frame, DisplayInsideArrayPanel displayInsideArrayPanel) {
        this.displayInsideArrayPanel = displayInsideArrayPanel;
        this.frame = frame;
    }

    public void setDeclaredMemberListPanel(DeclaredMemberListPanel declaredMemberListPanel) {
        this.declaredMemberListPanel = declaredMemberListPanel;
    }

    @Override
    void setPanelLayout() {
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
    }

    @Override
    void setupComponent() {

    }

    /**
     * 配列パネルを表示する
     */
    public void showArrayPanel() {
        this.add(displayInsideArrayPanel, BorderLayout.EAST);
        frame.setArrayPanelVisible(true);
        this.revalidate();
    }

    /**
     * 配列表示パネル内のリストを設定する
     * @param clazz 配列の型のクラス
     * @param length 配列の大きさ
     */
    public void makeArray(Class<?> clazz, int length) {
        displayInsideArrayPanel.makeList(clazz, length);
    }

    /**
     * 配列パネルを非表示する
     */
    public void removeArrayPanel() {
        this.remove(displayInsideArrayPanel);
        frame.setArrayPanelVisible(false);
        this.revalidate();
    }

    public void setupMemberList(Class<?> clazz) {
        declaredMemberListPanel.setupJListMember(clazz);
    }

}
