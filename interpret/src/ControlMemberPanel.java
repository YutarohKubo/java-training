import javax.swing.*;
import java.awt.*;
import java.lang.reflect.*;

public class ControlMemberPanel extends InterpretPanel {

    public static final String TAG_DUMMY_SHOW = "dummy_panel";
    public static final String TAG_FIELD_SHOW = "field_panel";
    public static final String TAG_METHOD_SHOW = "method_panel";
    public static final String TAG_CONSTRUCTOR_SHOW = "constructor_panel";

    private CardLayout layout;
    private ControlFieldPanel fieldPanel;
    private ControlMethodPanel methodPanel;
    private ControlConstructorPanel constructorPanel;

    public ControlFieldPanel getFieldPanel() {
        return fieldPanel;
    }

    public ControlMethodPanel getMethodPanel() {
        return methodPanel;
    }

    public ControlConstructorPanel getConstructorPanel() {
        return constructorPanel;
    }

    public ControlMemberPanel(ControlFieldPanel fieldPanel, ControlMethodPanel methodPanel, ControlConstructorPanel constructorPanel) {
        this.fieldPanel = fieldPanel;
        this.methodPanel = methodPanel;
        this.constructorPanel = constructorPanel;
        addComponent();
    }

    @Override
    void setPanelLayout() {
        this.layout = new CardLayout();
        setLayout(layout);
    }

    @Override
    void setupComponent() {

    }

    private void addComponent() {
        this.add(new JPanel(), TAG_DUMMY_SHOW);
        this.add(fieldPanel, TAG_FIELD_SHOW);
        this.add(methodPanel, TAG_METHOD_SHOW);
        this.add(constructorPanel, TAG_CONSTRUCTOR_SHOW);
        //setComponentMode(MemberType.FIELD);
    }

    public void setExecuteButtonState(MemberType type, boolean enable) {
        switch (type) {
            case CONSTRUCTOR:
                constructorPanel.setExecuteButtonState(enable);
                break;
            case METHOD:
                methodPanel.setExecuteButtonState(enable);
                break;
            case FIELD:
                fieldPanel.setExecuteButtonState(enable);
                break;
        }
    }

    public ControlConstructorPanel.DataHolder getConstructorPanelDataHolder() {
        return constructorPanel.getDataHolder();
    }

    public void setComponentMode(MemberType type) {
        switch (type) {
            case FIELD:
                layout.show(this, TAG_DUMMY_SHOW);
                layout.show(this, TAG_FIELD_SHOW);
                fieldPanel.setFieldDataComponent();
                break;
            case METHOD:
                layout.show(this, TAG_DUMMY_SHOW);
                layout.show(this, TAG_METHOD_SHOW);
                methodPanel.setMethodDataComponent();
                break;
            case CONSTRUCTOR:
                layout.show(this, TAG_DUMMY_SHOW);
                layout.show(this, TAG_CONSTRUCTOR_SHOW);
                constructorPanel.setConstructorDataComponent();
            default:
                //throw new IllegalArgumentException();
        }
    }

    public void setTargetMemberData(MemberData targetMemberData, MemberType type) {
        switch (type) {
            case FIELD:
                fieldPanel.setTargetFieldData(targetMemberData);
                break;
            case METHOD:
                methodPanel.setTargetMethodData(targetMemberData);
                break;
            case CONSTRUCTOR:
                constructorPanel.setTargetConstructorData(targetMemberData);
                break;
            default:
        }
    }
}
