package ch03.ex15;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ImageUtil {

    public static Color[][] toColorArray(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        Color[][] out = new Color[height][width];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out[y][x] = image.getPixelReader().getColor(x, y);
            }
        }
        return out;
    }

}
