package ch03.ex05;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Main {

    /** 縁の太さ */
    private static final int IMG_FLAME_STROKE_WIDTH = 10;

    private static Image transform(Image in, ColorTransformer transformer) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, transformer.apply(x, y, in.getPixelReader().getColor(x, y)));
            }
        }
        return out;
    }

    public static void main(String[] args) {
        Image img = new Image("path");
        int imgWidth = (int) img.getWidth();
        int imgHeight = (int) img.getHeight();
        Image newImage
                = transform(img, (x, y, colorAtXY) -> {
            if (x < IMG_FLAME_STROKE_WIDTH || x >= imgWidth - IMG_FLAME_STROKE_WIDTH ||
                    y < IMG_FLAME_STROKE_WIDTH || y >= imgHeight - IMG_FLAME_STROKE_WIDTH) {
                return Color.SILVER;
            }
            return colorAtXY;
        });
    }

}
