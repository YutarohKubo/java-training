package ch03.ex08;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.function.BiFunction;

public class Main extends Application {

    private static final String CD = System.getProperty("user.dir");
    private static final String IMAGE_FILE_PATH = CD + "\\src\\ch03\\ex05\\ouchi.jpg";

    /** 縁の太さ */
    private static final int IMG_FLAME_STROKE_WIDTH = 10;

    private static Image transform(Image in, ColorTransformer transformer) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, transformer.apply(x, y));
            }
        }
        return out;
    }

    private static ColorTransformer frameOperator(Image img, Color frameColor, int colorWidth) {
        return (x, y) -> {
            if (x < IMG_FLAME_STROKE_WIDTH || x >= img.getWidth() - colorWidth ||
                    y < IMG_FLAME_STROKE_WIDTH || y >= img.getHeight() - colorWidth) {
                return frameColor;
            }
            return img.getPixelReader().getColor(x, y);
        };
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane pane = new StackPane();

        File file = new File(IMAGE_FILE_PATH);
        Image img = new Image(file.toURI().toString());

        Image newImage
                = transform(img, frameOperator(img, Color.SILVER, 10));

        ImageView imageView = new ImageView(newImage);

        Scene scene = new Scene(pane, img.getWidth(), img.getHeight());
        primaryStage.setScene(scene);
        primaryStage.setTitle("FXTest");
        primaryStage.setResizable(false);

        pane.getChildren().add(imageView);
        primaryStage.show();
    }
}
