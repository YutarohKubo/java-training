package dc3_4;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class AppColorChoiceCell extends ListCell<AppColorData> {

    private HBox cellContainer;
    private Rectangle rect;
    private Text text;

    public AppColorChoiceCell() {
        setPrefWidth(100);
        initComponent();
    }

    private void initComponent() {
        cellContainer = new HBox(5);
        rect = new Rectangle(20, 20);
        text = new Text();
        cellContainer.getChildren().add(rect);
        cellContainer.getChildren().add(text);
    }

    @Override
    protected void updateItem(AppColorData item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            rect.setFill(item.getColor());
            text.setText(item.getName());
            setGraphic(cellContainer);
        } else {
            setGraphic(null);
        }
    }
}
