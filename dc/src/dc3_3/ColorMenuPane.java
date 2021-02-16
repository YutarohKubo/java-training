package dc3_3;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public class ColorMenuPane extends HBox {

    private Rectangle rect;
    private Label text;

    public ColorMenuPane(AppColorData colorData) {
        rect = new Rectangle(20, 20);
        text = new Label();
        rect.setFill(colorData.getColor());
        text.setText(colorData.getName());
        text.setMinWidth(100);
        getChildren().add(rect);
        getChildren().add(text);
        setSpacing(5);
    }

}
