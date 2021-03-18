package dc3_4;

import javafx.scene.control.ListCell;

public class AppFontSizeCell extends ListCell<AppFontSizeData> {

    @Override
    protected void updateItem(AppFontSizeData item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            setText(item.getName());
        } else {
            setText(null);
        }
    }

}
