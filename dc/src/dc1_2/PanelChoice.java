package dc1_2;

import java.awt.*;

public class PanelChoice extends Panel {

    private Choice mChoice;

    public PanelChoice(String title, String[] array, String defaultItem) {
        this.setLayout(new FlowLayout());
        Label label = new Label(title);
        mChoice = new Choice();
        for (String item : array) {
            mChoice.add(item);
        }
        mChoice.select(defaultItem);
        this.add(label);
        this.add(mChoice);
    }

    public String getSelectedItem () {
        return mChoice.getSelectedItem();
    }
}
