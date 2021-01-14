package dc3_2;

import javafx.scene.paint.Color;

public class AppColorData {

    private Color color;
    private String name;

    public AppColorData(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
